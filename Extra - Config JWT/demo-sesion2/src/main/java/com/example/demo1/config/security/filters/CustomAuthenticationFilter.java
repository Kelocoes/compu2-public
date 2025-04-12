package com.example.demo1.config.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo1.config.security.authentication.CustomAuthentication;
import com.example.demo1.config.security.authentication.CustomAuthenticationJwt;
import com.example.demo1.config.security.managers.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Value("${app.security.jwt.token-prefix}")
    private String tokenPrefix;

    @Value("${app.security.jwt.header-string}")
    private String headerString;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = null;

        String key = request.getHeader("key");
        if (key != null) {
            // Custom authentication logic with api key
            CustomAuthentication customAuthentication = new CustomAuthentication(false, key);
            authentication = customAuthenticationManager.authenticate(customAuthentication);
        } else if (request.getHeader(headerString) != null && request.getHeader(headerString).startsWith(tokenPrefix)) {
            // Custom authentication logic with JWT
            String token = request.getHeader(headerString).substring(tokenPrefix.length());
            CustomAuthenticationJwt customAuthentication = new CustomAuthenticationJwt(true, token);
            authentication = customAuthenticationManager.authenticate(customAuthentication);
        }

        if (authentication != null && authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}

