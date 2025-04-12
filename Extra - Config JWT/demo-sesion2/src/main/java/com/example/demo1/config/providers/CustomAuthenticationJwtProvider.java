package com.example.demo1.config.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo1.config.security.authentication.CustomAuthenticationJwt;
import com.example.demo1.service.IJwtService;

@Component
public class CustomAuthenticationJwtProvider implements AuthenticationProvider{
    
    @Autowired
    private IJwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        // Verificamos si el token es válido
        String token = ((CustomAuthenticationJwt) authentication).getToken().replace("Bearer ", "").trim();


        String username = jwtService.extractUsername(token);
        if (username != null && !jwtService.isTokenExpired(token)) {
            // Si el token es válido, devolvemos la autenticación
            return new CustomAuthenticationJwt(true, token);
        }

        throw new BadCredentialsException("Malas credenciales en JWT!!");
    }
    
    @Override 
    public boolean supports(Class<?> authentication) {
        // De esta manera el CustomAuthenticationManager sabrá cuál AuthProvider usar
        return CustomAuthenticationJwt.class.equals(authentication); 
    }
    
}
