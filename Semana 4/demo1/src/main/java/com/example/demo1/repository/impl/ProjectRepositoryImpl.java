package com.example.demo1.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Project;
import com.example.demo1.repository.IProjectRepository;
import com.example.demo1.repository.ITaskRepository;

@Repository
public class ProjectRepositoryImpl implements IProjectRepository{

    private List<Project> projects = new ArrayList<>();
    private ITaskRepository taskRepository;

    @Value("#{'production'.equals('${app.environment}')}")
    private boolean isProduction;

    public ProjectRepositoryImpl() {
        super();
    }

    @Autowired
    public ProjectRepositoryImpl(ITaskRepository taskRepository) {
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
}
