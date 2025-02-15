package com.example.demo2.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo2.model.Project;
import com.example.demo2.repository.IProjectRepository;
import com.example.demo2.repository.ITaskRepository;

@Component
public class ProjectRepositoryImpl2 implements IProjectRepository{
    
    private List<Project> projects = new ArrayList<>();
    private ITaskRepository taskRepository;

    @Value("#{'production'.equals('${app.environment}')}")
    private boolean isProduction;

    public ProjectRepositoryImpl2() {
        super();
    }

    @Autowired
    public ProjectRepositoryImpl2(ITaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Override
    public Project save(Project project) {
        Project existingProject = findById(project.getId()).orElse(null);

        if (existingProject == null) {
            //Crearlo
            projects.add(project);
            
            // Cuando creemos un proyecto, guardar tareas
            if (project.getTasks() != null) {
                project.getTasks().forEach(task -> taskRepository.save(task));
            }
        } else {
            //Actualizarlo
            projects.remove(existingProject);
            Project newProject = new Project(project);
            newProject.setTasks(project.getTasks());
            projects.add(newProject);
        }

        return project;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projects.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public List<Project> findAll() {
        return projects;
    }

    @Override
    public void init() {
        System.out.println("Init");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy");
    }

    public ITaskRepository getTaskRepository() {
        return taskRepository;
    }

    public void setTaskRepository(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
