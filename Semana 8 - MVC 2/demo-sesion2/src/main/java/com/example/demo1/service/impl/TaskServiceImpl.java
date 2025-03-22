package com.example.demo1.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo1.model.Task;
import com.example.demo1.model.TaskStatus;
import com.example.demo1.repository.ITaskRepository;
import com.example.demo1.service.ITaskService;

import jakarta.transaction.Transactional;

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
        // return taskRepository.save(task);
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    @Transactional
    public int deleteTaskByProjectId(Long projectId) {
        return taskRepository.deleteTaskByProjectId(projectId);
    }

    @Override
    @Transactional
    public int deleteCompletedTasks() {
        return taskRepository.deleteCompletedTasks();
    }

    @Override
    @Transactional
    public int updateTaskStatus(Long taskId, TaskStatus status) {
        return taskRepository.updateTaskStatus(taskId, status);
    }

    @Override
    public Task findByName(String name) {
        return taskRepository.findByName(name).orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
