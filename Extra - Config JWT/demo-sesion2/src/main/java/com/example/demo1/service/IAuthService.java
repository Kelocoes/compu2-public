package com.example.demo1.service;

import com.example.demo1.dto.AuthDtos.LoginRequestDTO;
import com.example.demo1.dto.AuthDtos.RegisterRequestDTO;
import com.example.demo1.dto.AuthDtos.TokenResponseDTO;

public interface IAuthService {

    TokenResponseDTO register(RegisterRequestDTO request);

    TokenResponseDTO login(LoginRequestDTO request);
}
