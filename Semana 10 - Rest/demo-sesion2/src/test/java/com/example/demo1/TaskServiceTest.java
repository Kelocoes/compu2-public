package com.example.demo1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo1.model.Task;
import com.example.demo1.repository.ITaskRepository;
import com.example.demo1.service.impl.TaskServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
class TaskServiceTest {

	@Autowired
	private ITaskRepository taskRepository;

	@Autowired
	private TaskServiceImpl taskService;

	@Test
	public void testFindTaskByIdSuccess() {
		Task taskCreated = new Task("Mi tarea");
		taskRepository.save(taskCreated);

		Task taskFound = taskService.findByName("Mi tarea");

		assertNotNull(taskFound);
		assertEquals("Mi tarea", taskFound.getName());
		
	}
}
