package com.example.demo1.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo1.model.Task;
import com.example.demo1.repository.ITaskRepository;
import com.example.demo1.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService{

    private ITaskRepository taskRepository;

    public TaskServiceImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
        // throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
