package com.example.demo1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Task;
import com.example.demo1.model.TaskStatus;

@Repository
public interface ITaskRepository  extends JpaRepository<Task, Long>{

    // El modifying es para que sepa que es una operación de escritura
    // El clearAutomatically = true es para que se limpie el contexto de persistencia
    // Lo que hace que se sincronice la base de datos con el contexto de persistencia
    @Modifying(clearAutomatically = true)
    @Query("DELETE Task t WHERE t.project.id = :projectId")
    int deleteTaskByProjectId(Long projectId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE Task t WHERE t.status = com.example.demo1.model.TaskStatus.DONE")
    int deleteCompletedTasks();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    int updateTaskStatus(@Param("id") Long taskId,@Param("status") TaskStatus status);

    Optional<Task> findByName(String name);
}



