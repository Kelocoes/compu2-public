package com.example.demo2.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo2.model.Project;

public interface IProjectRepository {
    
    Project save(Project project);

    Optional<Project> findById(Long id);

    List<Project> findAll();

    void init();

    void destroy();
}
