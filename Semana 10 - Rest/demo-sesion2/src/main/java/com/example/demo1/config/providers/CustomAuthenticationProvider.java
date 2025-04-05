package com.example.demo1.config.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo1.config.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
    
    @Value("${custom.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        String headerKey = customAuthentication.getKey();

        if (key.equals(headerKey)) {
            return new CustomAuthentication(true, null);
        }

        throw new BadCredentialsException("Malas credenciales!!");
    }
    
    @Override 
    public boolean supports(Class<?> authentication) {
        // De esta manera el CustomAuthenticationManager sabrá cuál AuthProvider usar
        return CustomAuthentication.class.equals(authentication); 
    }
    
}
