package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.UserRole;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    
}
