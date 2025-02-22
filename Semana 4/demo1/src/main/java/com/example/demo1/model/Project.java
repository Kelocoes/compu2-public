package com.example.demo1.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    
    private Long id;
    private String name;
    private LocalDate dateCreated;
    private List<Task> tasks;

    
    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
        this.dateCreated = LocalDate.now();
    }

    public Project(Project project) {
        this(project.getId(), project.getName());
    }
}
