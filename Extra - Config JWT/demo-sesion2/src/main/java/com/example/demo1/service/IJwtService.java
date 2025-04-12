package com.example.demo1.service;

import org.springframework.security.core.Authentication;

import com.example.demo1.model.User;
import com.example.demo1.model.Worker;

public interface IJwtService {
    String generateAccessToken(User user, Worker worker, Authentication auth);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
}
