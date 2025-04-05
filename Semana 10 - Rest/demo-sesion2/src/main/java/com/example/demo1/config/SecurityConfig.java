package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    // Autenticación personalizada con un solo filer chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(t -> t.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/customAuth/**") // Desactiva CSRF solo para rutas que coincidan con /customAuth/**
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/customAuth/**").authenticated()
                .anyRequest().authenticated()
            )
            .authenticationManager(customAuthenticationManager)
            .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(withDefaults()) // Configuración básica para todas las rutas
            .formLogin(form -> form.defaultSuccessUrl("/projects", true)) // Redirigir a /projects después de iniciar sesión
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


