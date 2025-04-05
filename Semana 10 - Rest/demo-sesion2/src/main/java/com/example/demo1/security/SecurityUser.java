package com.example.demo1.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo1.model.RolePermission;
import com.example.demo1.model.User;
import com.example.demo1.model.UserRole;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final User user;

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SecurityAuthority> authorities = user.getUserRoles().stream()
            .map(UserRole::getRole) // Obtiene los roles de cada UserRole
            .flatMap(role -> role.getRolePermissions().stream()) // Obtiene los RolePermissions de cada Role
            .map(RolePermission::getPermission) // Obtiene los permisos de cada RolePermission
            .map(SecurityAuthority::new) // Convierte cada Permission en un SecurityAuthority
            .collect(Collectors.toList()); // Recopila todo en una lista
        return authorities;
    }

}
