package com.example.demo1.service;

import java.util.Optional;

import com.example.demo1.model.Project;


public interface IProjectService {

    Iterable<Project> findAll();
    
    Optional<Project> findById(Long id);

    Project save(Project project);

    void createProjectWithTasks();
}
