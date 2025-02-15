package com.example.demo1;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo1.model.Project;
import com.example.demo1.model.Task;
import com.example.demo1.repository.IProjectRepository;
import com.example.demo1.repository.ITaskRepository;

public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // HolaMundo obj = (HolaMundo) context.getBean("HolaMundo");
        // System.out.println(obj.getMessage());

        // Real
        IProjectRepository projectRepository = (IProjectRepository) context.getBean("ProjectRepository");
        IProjectRepository projectRepository2 = (IProjectRepository) context.getBean("ProjectRepository");

        if (projectRepository == projectRepository2) {
            System.out.println("Singleton");
        } else {
            System.out.println("Prototype");
        }

        projectRepository.save(new Project(1L, "Description 1"));

        List<Project> projects = projectRepository.findAll();
        projects.forEach(System.out::println);


        Project projectWithTasks = new Project(2L, "Description 2");
        projectWithTasks.setTasks(List.of(new Task(1L, "Task 1"),
            new Task(2L, "Task 2"),
            new Task(3L, "Task 3")
        ));

        projectRepository.save(projectWithTasks);

        ITaskRepository taskRepository = (ITaskRepository) context.getBean("TaskRepository");
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(System.out::println);

        context.close();
    }
}
