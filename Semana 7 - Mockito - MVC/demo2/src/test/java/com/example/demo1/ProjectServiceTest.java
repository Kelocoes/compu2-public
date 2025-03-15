package com.example.demo1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo1.service.IProjectService;
import com.example.demo1.service.impl.ProjectServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
class ProjectServiceTest {

	@Autowired
	private IProjectService projectRepository;

	@Autowired
	private ProjectServiceImpl taskService;

	@Test
	public void saveProject_ShouldThrowException_WhenTransactional() {
		UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
			taskService.createProjectWithTasks();
		});
		assertNotNull(exception);
		assertEquals(Optional.empty(), projectRepository.findByNameNative("Project 1"));
		assertEquals("Not implemented yet", exception.getMessage());
	}
}
