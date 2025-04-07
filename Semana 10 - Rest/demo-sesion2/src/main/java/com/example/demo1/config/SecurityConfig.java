package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo1.config.security.filters.CustomAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import com.example.demo1.config.security.managers.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationFilter customAuthenticationFilter;
    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain statelessSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/customAuth/**") // Aplica esta configuración solo a las rutas /customAuth/**
            .cors(t -> t.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/customAuth/**") // Desactiva CSRF solo para estas rutas
            )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Todas las rutas bajo /customAuth/** requieren autenticación
            )
            .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless para estas rutas
            .authenticationManager(customAuthenticationManager)
            .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(withDefaults()) // Configuración básica
            .build();
    }

    @Bean
    public SecurityFilterChain statefulSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/**") // Aplica esta configuración al resto de las rutas
            .cors(withDefaults())
            .csrf(withDefaults()) // CSRF habilitado para rutas fuera de /customAuth/**
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)) // Stateful para estas rutas
            .formLogin(form -> form.defaultSuccessUrl("/projects", true)) // Redirigir a /projects después de iniciar sesión
            .httpBasic(withDefaults()) // Configuración básica
            .build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // Permitir credenciales, esto significa que 
        configuration.addAllowedOriginPattern("*"); // Permitir cualquier origen
        configuration.addAllowedHeader("*"); // Permitir cualquier encabezado
        configuration.addAllowedMethod("*"); // Permitir cualquier método (GET, POST, PUT, DELETE, etc.)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}


