package com.example.demo1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo1.model.Project;


public interface IProjectService {

    Iterable<Project> findAll();
    
    Optional<Project> findById(Long id);

    Project save(Project project);

    void createProjectWithTasks();

    List<Project> findByDateCreatedGreaterThan(LocalDate dateCreated);

    List<Project> findByDateCreatedLessThan(LocalDate dateCreated);

    List<Project> findByDateCreatedLessThanEqual(LocalDate dateCreated);

    List<List<Integer>> countByYearCreated();

    Optional<Project> findByNameNative(String name);

    List<Project> findByIdIn(List<Long> ids);

    List<Project> findByNameParam(String name);
}
