package com.example.demo1.model;

import java.time.LocalDate;
import java.util.List;

public class Project {

    private Long id;
    private String name;
    private LocalDate dateCreated;
    private List<Task> tasks;

    public Project() { }
    
    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
        this.dateCreated = LocalDate.now();
    }

    public Project(Project project) {
        this(project.getId(), project.getName());
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + name + ", dateCreated=" + dateCreated + "]";
    }

}