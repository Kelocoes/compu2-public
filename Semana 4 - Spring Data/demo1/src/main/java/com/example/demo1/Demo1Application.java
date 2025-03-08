package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.PropertySource;

import com.example.demo1.service.IProjectService;
import com.example.demo1.service.ITaskService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
// @PropertySource("classpath:application2.properties")
public class Demo1Application{

	@Autowired
	IProjectService projectService;

	@Autowired
	ITaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@PostConstruct
	public void PostConstruct() {
		try {
			projectService.createProjectWithTasks();
		} catch (Exception e) {
			System.out.println("Error creating project with tasks: " + e.getMessage());
		}

		System.out.println("Projects:");
		projectService.findAll().forEach(p -> System.out.println(p.toString()));

		System.out.println("Tasks:");
		taskService.findAll().forEach(t -> System.out.println(t.toString()));
	}
}
