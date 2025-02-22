package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Task;

@Repository
public interface ITaskRepository  extends JpaRepository<Task, Long>{
}

