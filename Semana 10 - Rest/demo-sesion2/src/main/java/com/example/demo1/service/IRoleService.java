package com.example.demo1.service;

import java.util.List;
import java.util.Optional;

import com.example.demo1.model.Role;

public interface IRoleService {

    List<Role> findAll();

    Optional<Role> findById(Long id);

    Role save(Role role);
} 