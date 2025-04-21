package com.example.demo1.dto.AuthDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponseDTO{
    
    String accessToken;
    String tokenType;
}
