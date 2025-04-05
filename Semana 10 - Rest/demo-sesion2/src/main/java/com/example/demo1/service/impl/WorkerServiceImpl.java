package com.example.demo1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo1.model.Worker;
import com.example.demo1.repository.IWorkerRepository;
import com.example.demo1.service.IWorkerService;

public class WorkerServiceImpl implements IWorkerService{
    
    @Autowired
    private IWorkerRepository workerRepository;

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    @Override
    public Optional<Worker> findById(Long id) {
        return workerRepository.findById(id);
    }

    @Override
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }
}
