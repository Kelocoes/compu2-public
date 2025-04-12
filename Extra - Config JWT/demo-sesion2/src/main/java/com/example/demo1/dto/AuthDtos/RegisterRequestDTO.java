package com.example.demo1.dto.AuthDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String documentId;
}
