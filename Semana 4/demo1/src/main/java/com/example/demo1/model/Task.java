package com.example.demo1.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    
    private Long id;
    private String name;
    private String description;
    private LocalDate dateCreated;
    private LocalDate dueDate;
    private TaskStatus status;

    public Task(Task task) {
        this(task.getId(), task.getName(), task.getDescription(), task.getDateCreated(), task.getDueDate(), task.getStatus());
    }

    public Task(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
