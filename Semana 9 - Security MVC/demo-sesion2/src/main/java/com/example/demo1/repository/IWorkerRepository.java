package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.Worker;

public interface IWorkerRepository extends JpaRepository<Worker, Long> {
    
}
