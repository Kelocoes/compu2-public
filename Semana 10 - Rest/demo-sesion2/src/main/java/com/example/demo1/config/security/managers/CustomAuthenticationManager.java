package com.example.demo1.config.security.managers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo1.config.providers.CustomAuthenticationProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Si el tipo de autenticación es compatible con el CustomAuthenticationProvider, lo utilizamos
        if (customAuthenticationProvider.supports(authentication.getClass())) {
            return customAuthenticationProvider.authenticate(authentication);
        }
        // Si es compatible con el DaoAuthenticationProvider, lo utilizamos
        else if (daoAuthenticationProvider.supports(authentication.getClass())) {
            return daoAuthenticationProvider.authenticate(authentication);
        }
        // Si ningún proveedor soporta la autenticación, lanzamos una excepción
        throw new BadCredentialsException("No se pudo autenticar");
    }
}
