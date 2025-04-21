package com.example.demo1.security;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo1.model.Permission;
import com.example.demo1.model.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority{

    private Permission permission;

    @Override
    public String getAuthority() {
        return permission.getName();
    }
    
}
