package com.example.demo1.service;

import java.util.List;
import java.util.Optional;

import com.example.demo1.model.User;

public interface IUserService {
    
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> findByUsername(String username);
}
