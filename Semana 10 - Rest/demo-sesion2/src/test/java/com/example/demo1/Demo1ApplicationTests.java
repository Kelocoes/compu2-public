package com.example.demo1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// Statics for Mockito
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo1.model.Project;
import com.example.demo1.repository.IProjectRepository;
import com.example.demo1.service.impl.ProjectServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
class Demo1ApplicationTests {

	@Value("${app.environment}")
	private String appEnv;

	@Mock
	private IProjectRepository projectRepository;

	@InjectMocks
	private ProjectServiceImpl projectService;

	@Test
	void contextLoads() {
		System.out.println("App environment: " + appEnv);
		assertEquals("test", appEnv);
	}

	@Test
	public void testFindProjectByIdSucess() {
		Project mockProject = new Project(1L, "Mi proyecto", LocalDate.now());
		when(projectRepository.findById(1L)).thenReturn(Optional.of(mockProject));

		Optional<Project> projectFound = projectService.findById(1L);

		assertNotNull(projectFound);
		assertEquals("Mi proyecto", projectFound.get().getName());
		
		// Verify that the method findById was called only once
		verify(projectRepository, times(1)).findById(1L);
	}

	@Test
	public void testFindProjectByIdNull() {
		when(projectRepository.findById(1L)).thenReturn(Optional.empty());

		Optional<Project> projectFound = projectService.findById(1L);

		assertEquals(Optional.empty(), projectFound);

		verify(projectRepository, times(1)).findById(1L);
	}

}
