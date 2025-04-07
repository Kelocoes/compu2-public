package com.example.demo1.service;

import java.util.List;
import java.util.Optional;

import com.example.demo1.model.Permission;

public interface IPermissionService {
    
    List<Permission> findAll();

    Optional<Permission> findById(Long id);

    Permission save(Permission permission);
}
