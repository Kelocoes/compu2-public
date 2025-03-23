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

    // Autenticación personalizada
    @Bean
    public SecurityFilterChain customAuthFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/customAuth/**")  // Aplica solo a rutas que comienzan con /customAuth
            .authenticationManager(customAuthenticationManager)
            .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .build();
    }

    // Autenticación por defecto
    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/**")  // Aplica a todas las demás rutas
                .authenticationManager(customAuthenticationManager)
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .build();
    }
}


