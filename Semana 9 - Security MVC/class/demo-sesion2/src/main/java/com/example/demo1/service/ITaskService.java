package com.example.demo1.service;

import com.example.demo1.model.Task;
import com.example.demo1.model.TaskStatus;

public interface ITaskService {
    
    Iterable<Task> findAll();

    Task save(Task task);

    int deleteTaskByProjectId(Long projectId);

    int deleteCompletedTasks();

    int updateTaskStatus(Long taskId, TaskStatus status);

    Task findByName(String name);
}
