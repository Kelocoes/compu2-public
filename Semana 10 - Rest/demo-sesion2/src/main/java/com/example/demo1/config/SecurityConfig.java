package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
}


