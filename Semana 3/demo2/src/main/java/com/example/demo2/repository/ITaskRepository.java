package com.example.demo2.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo2.model.Task;

public interface ITaskRepository {
    
    Task save(Task Task);

    Optional<Task> findById(Long id);

    List<Task> findAll();
}

