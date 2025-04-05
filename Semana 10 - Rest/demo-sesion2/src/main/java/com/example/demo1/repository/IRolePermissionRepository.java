package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.RolePermission;

public interface IRolePermissionRepository extends JpaRepository<RolePermission, Long> {
    
}
