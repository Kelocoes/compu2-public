package com.example.demo1.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo1.model.User;
import com.example.demo1.model.Worker;
import com.example.demo1.service.IJwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements IJwtService {

    @Value("${app.security.jwt.secret-key}")
    private String secretKey;

    @Value("${app.security.jwt.expiration-time}")
    private long expirationTime;

    @Override
    public String generateAccessToken(User user, Worker worker, Authentication auth) {
        return buildToken(user, worker, expirationTime, auth);
    }

    private String buildToken(User user, Worker worker, long expirationTime, Authentication auth) {
        return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of(
                    "username", user.getUsername(),
                    "email", user.getEmail(),
                    "name", worker.getName(),
                    "lastname", worker.getLastname(),
                    "documentId", worker.getDocumentId(),
                    "userId", user.getId().toString(),
                    "workerId", worker.getId().toString(),
                    "authorities", auth != null && auth.getAuthorities() != null ? auth.getAuthorities().stream()
                        .map(ga -> ga.getAuthority())
                        .toList() : List.of()
                ))
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    @Override
    public boolean isTokenExpired(String token) {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration()
            .before(new Date());
    }
}
