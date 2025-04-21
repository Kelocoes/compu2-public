package com.example.demo1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo1.model.Worker;

public interface IWorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value = "SELECT * FROM Worker w WHERE w.user_id = :userId", nativeQuery = true)
    Optional<Worker> findByUserId(@Param("userId") Long userId);
}
