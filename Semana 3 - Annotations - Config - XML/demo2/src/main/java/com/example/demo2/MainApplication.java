package com.example.demo2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.demo2.model.Project;
import com.example.demo2.model.Task;
import com.example.demo2.repository.IProjectRepository;
import com.example.demo2.repository.ITaskRepository;

import jakarta.annotation.PostConstruct;

// @Configuration
// @ComponentScan(basePackages = "com.example.demo2")
// public class MainApplication {

//     public static void main(String[] args) {
//         ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainApplication.class);
//         // HolaMundo obj = (HolaMundo) context.getBean("HolaMundo");
//         // System.out.println(obj.getMessage());

//         // Real
//         IProjectRepository projectRepository = (IProjectRepository) context.getBean("projectRepositoryImpl", IProjectRepository.class);
//         IProjectRepository projectRepository2 = (IProjectRepository) context.getBean("projectRepositoryImpl", IProjectRepository.class);

//         if (projectRepository == projectRepository2) {
//             System.out.println("Singleton");
//         } else {
//             System.out.println("Prototype");
//         }

//         projectRepository.save(new Project(1L, "Description 1"));

//         List<Project> projects = projectRepository.findAll();
//         projects.forEach(System.out::println);


//         Project projectWithTasks = new Project(2L, "Description 2");
//         projectWithTasks.setTasks(List.of(new Task(1L, "Task 1"),
//             new Task(2L, "Task 2"),
//             new Task(3L, "Task 3")
//         ));

//         projectRepository.save(projectWithTasks);

//         ITaskRepository taskRepository = (ITaskRepository) context.getBean("taskRepositoryImpl", ITaskRepository.class);
//         List<Task> tasks = taskRepository.findAll();
//         tasks.forEach(System.out::println);

//         context.close();
//     }
// }


@Configuration
@ComponentScan(basePackages = "com.example.demo2")
public class MainApplication {

    @Autowired
    // @Qualifier("projectRepositoryImpl2")
    IProjectRepository projectRepository;
    @Autowired
    ITaskRepository taskRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainApplication.class);
    }

    @PostConstruct
    public void init() {

        projectRepository.save(new Project(1L, "Description 1"));

        List<Project> projects = projectRepository.findAll();
        projects.forEach(System.out::println);


        Project projectWithTasks = new Project(2L, "Description 2");
        projectWithTasks.setTasks(List.of(new Task(1L, "Task 1"),
            new Task(2L, "Task 2"),
            new Task(3L, "Task 3")
        ));

        projectRepository.save(projectWithTasks);
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(System.out::println);
    }

}