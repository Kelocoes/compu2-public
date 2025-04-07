package com.example.demo1.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo1.model.Project;
import com.example.demo1.model.Task;
import com.example.demo1.repository.IProjectRepository;
import com.example.demo1.service.IProjectService;
import com.example.demo1.service.ITaskService;

import jakarta.transaction.Transactional;

@Service
public class ProjectServiceImpl implements IProjectService{
    
    private IProjectRepository projectRepository;

    private ITaskService taskService;

    public ProjectServiceImpl(IProjectRepository projectRepository, ITaskService taskService) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createProjectWithTasks() {
        Project project = new Project("Project 1", LocalDate.now());

        Project newProject = save(project);

        Task task1 = new Task("Task 1");
        task1.setProject(newProject);
        taskService.save(task1);

        Task task2 = new Task("Task 2");
        task2.setProject(newProject);
        taskService.save(task2);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        newProject.setTasks(tasks);
        
        save(newProject);
    }

    @Override
    public List<Project> findByDateCreatedGreaterThan(LocalDate dateCreated) {
        return projectRepository.findByDateCreatedGreaterThan(dateCreated);
    }

    @Override
    public List<Project> findByDateCreatedLessThan(LocalDate dateCreated) {
        return projectRepository.findByDateCreatedLessThan(dateCreated);
    }

    @Override
    public List<Project> findByDateCreatedLessThanEqual(LocalDate dateCreated) {
        return projectRepository.findByDateCreatedLessThanEqual(dateCreated);
    }

    @Override
    public List<List<Integer>> countByYearCreated() {
        return projectRepository.countByYearCreated();
    }

    @Override
    public Optional<Project> findByNameNative(String name) {
        return projectRepository.findByNameNative(name);
    }

    @Override
    public List<Project> findByIdIn(List<Long> ids) {
        return projectRepository.findByIdIn(ids);
    }

    @Override
    public List<Project> findByNameParam(String name) {
        return projectRepository.findByNameParam(name);
    }

    @Override
    public Long deleteById(Long id) {
        projectRepository.deleteById(id);
        return id;
    }
}
