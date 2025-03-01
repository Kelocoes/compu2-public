package com.example.demo1;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
// import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

import com.example.demo1.model.TaskStatus;
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

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		System.out.println("Projects created after a specific date:");
		projectService.findByDateCreatedGreaterThan(LocalDate.of(2023, 1, 1)).forEach(p -> System.out.println(p.toString()));

		System.out.println("Projects created before a specific date:");
		projectService.findByDateCreatedLessThan(LocalDate.of(2023, 1, 1)).forEach(p -> System.out.println(p.toString()));

		System.out.println("Projects created on or before a specific date:");
		projectService.findByDateCreatedLessThanEqual(LocalDate.of(2023, 1, 1)).forEach(p -> System.out.println(p.toString()));

		System.out.println("Project count by year created:");
		projectService.countByYearCreated().forEach(count -> System.out.println(count.toString()));

		System.out.println("Projects with a specific name (native query):");
		projectService.findByNameNative("ProjectName").forEach(p -> System.out.println(p.toString()));

		System.out.println("Projects with specific IDs:");
		projectService.findByIdIn(Arrays.asList(1L, 2L, 3L)).forEach(p -> System.out.println(p.toString()));

		System.out.println("Projects with a specific name (parameterized query):");
		projectService.findByNameParam("ProjectName").forEach(p -> System.out.println(p.toString()));

		System.out.println("---------------------------------------");

		System.out.println("Deleting tasks by project ID:");
		int deletedTasksByProjectId = taskService.deleteTaskByProjectId(1L);
		System.out.println("Number of tasks deleted: " + deletedTasksByProjectId);

		System.out.println("Deleting completed tasks:");
		int deletedCompletedTasks = taskService.deleteCompletedTasks();
		System.out.println("Number of completed tasks deleted: " + deletedCompletedTasks);

		System.out.println("Updating task status:");
		int updatedTaskStatus = taskService.updateTaskStatus(4L, TaskStatus.TO_DO);
		System.out.println("Number of tasks updated: " + updatedTaskStatus);
	}
}
