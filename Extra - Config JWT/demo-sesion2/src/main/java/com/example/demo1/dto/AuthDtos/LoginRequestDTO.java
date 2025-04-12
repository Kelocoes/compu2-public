package com.example.demo1.dto.AuthDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    
    private String username;
    private String password;
}
