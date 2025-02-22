package com.example.demo1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.demo1.model.Project;
import com.example.demo1.model.Task;
import com.example.demo1.repository.IProjectRepository;
import com.example.demo1.repository.ITaskRepository;

@SpringBootApplication
@PropertySource("classpath:application.properties2")
public class Demo1Application implements CommandLineRunner{

	private static Logger logger = LoggerFactory.getLogger(Demo1Application.class);

	@Autowired
	private IProjectRepository projectRepository;

	@Autowired
	private ITaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		projectRepository.save(new Project(1L, "Description 1"));

        List<Project> projects = projectRepository.findAll();
        projects.forEach(System.out::println);


        Project projectWithTasks = new Project(2L, "Description 2");
        projectWithTasks.setTasks(List.of(new Task(1L, "Task 1"),
            new Task(2L, "Task 2"),
            new Task(3L, "Task 3")
        ));

        Project projectSaved = projectRepository.save(projectWithTasks);
		logger.info("Project with tasks saved: " + projectSaved);
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(System.out::println);
	}

}
