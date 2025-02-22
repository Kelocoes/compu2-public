package com.example.demo1.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo1.model.Task;
import com.example.demo1.repository.ITaskRepository;

@Repository
public class TaskRepositoryImpl implements ITaskRepository{

    private List<Task> tasks = new ArrayList<>();

    public TaskRepositoryImpl() {
        super();
    }

    @Override
    public Task save(Task task) {
        Task existingTask = findById(task.getId()).orElse(null);

        if (existingTask == null) {
            //Crearlo
            tasks.add(task);
        } else {
            //Actualizarlo
            tasks.remove(existingTask);
            Task newTask = new Task(task);
            tasks.add(newTask);
        }

        return task;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }
    
}
