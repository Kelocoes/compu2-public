package com.example.demo1.service;

import java.util.List;
import java.util.Optional;

import com.example.demo1.model.Worker;

public interface IWorkerService {
    
    List<Worker> findAll();

    Optional<Worker> findById(Long id);

    Worker save(Worker worker);
}
