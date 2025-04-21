package com.example.demo1.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthenticationManager customAuthenticationManager;
    private CustomAuthenticationFilter customAuthenticationFilter;
    
    @Bean // FilterChain
    public SecurityFilterChain customAuthFilterChain(HttpSecurity http) throws Exception{
        return http
            .securityMatcher("/customConApiKey/**")
            .authenticationManager(customAuthenticationManager)
            .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> 
                auth.anyRequest().authenticated())
            .build();
    }

    
}
