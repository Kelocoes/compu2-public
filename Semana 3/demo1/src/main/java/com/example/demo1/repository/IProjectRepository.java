package com.example.demo1.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo1.model.Project;

public interface IProjectRepository {
    
    Project save(Project project);

    Optional<Project> findById(Long id);

    List<Project> findAll();

    void init();

    void destroy();
}
