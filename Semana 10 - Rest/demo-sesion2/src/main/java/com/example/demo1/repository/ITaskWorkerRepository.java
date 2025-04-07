package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.TaskWorker;

public interface ITaskWorkerRepository extends JpaRepository<TaskWorker, Long> {
    
}
