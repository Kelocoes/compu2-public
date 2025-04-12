package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dto.AuthDtos.LoginRequestDTO;
import com.example.demo1.dto.AuthDtos.RegisterRequestDTO;
import com.example.demo1.dto.AuthDtos.TokenResponseDTO;
import com.example.demo1.service.IAuthService;

@RestController
@RequestMapping("/customAuth/api/public/auth")
public class AuthController {
    
    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        TokenResponseDTO token = authService.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO request) {
        TokenResponseDTO token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}
