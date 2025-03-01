package com.example.demo1.service;

import com.example.demo1.model.Task;

public interface ITaskService {
    
    Iterable<Task> findAll();

    Task save(Task task);
}
