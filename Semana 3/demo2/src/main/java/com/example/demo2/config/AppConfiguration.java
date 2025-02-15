package com.example.demo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.demo2.repository.IProjectRepository;
import com.example.demo2.repository.ITaskRepository;
import com.example.demo2.repository.impl.ProjectRepositoryImpl;
import com.example.demo2.repository.impl.TaskRepositoryImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Bean
    public String message() {
        return "Hola Mundo";
    }

    
    // @Bean("taskRepositoryImpl")
    // public ITaskRepository taskRepository() {
    //     return new TaskRepositoryImpl();
    // }

    // @Bean("projectRepositoryImpl")
    // public IProjectRepository projectRepository() {
    //     return new ProjectRepositoryImpl(taskRepository());
    // }
}