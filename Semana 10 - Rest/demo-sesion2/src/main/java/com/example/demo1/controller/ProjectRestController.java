package com.example.demo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dto.Projects.ProjectOutDTO;
import com.example.demo1.dto.Tasks.TaskOutDTO;
import com.example.demo1.mappers.ProjectMapper;
import com.example.demo1.model.Project;
import com.example.demo1.service.IProjectService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/customAuth/api/projects")
public class ProjectRestController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return projectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.status(201).body(projectService.save(project));
    }

    @GetMapping
    public ResponseEntity<List<ProjectOutDTO>> getProjects(@RequestParam(required = false) String name) {
        List<Project> projects = (name != null) ? projectService.findByNameParam(name) : projectService.findAll();
        // Manual way to map Project to ProjectAbstractDTO
        // List<ProjectOutDTO> projectOutDTOs = projects.stream().map(project -> {
        //     ProjectOutDTO dto = new ProjectOutDTO();
        //     dto.setId(project.getId());
        //     dto.setName(project.getName());
        //     dto.setTasks(
        //         project.getTasks().stream().map(task -> {
        //             TaskOutDTO taskOutDTO = new TaskOutDTO();
        //             taskOutDTO.setId(task.getId());
        //             taskOutDTO.setName(task.getName());
        //             taskOutDTO.setDescription(task.getDescription());
        //             taskOutDTO.setDateCreated(task.getDateCreated());
        //             taskOutDTO.setDueDate(task.getDueDate());
        //             taskOutDTO.setStatus(task.getStatus().toString());
        //             return taskOutDTO;
        //         }).toArray(TaskOutDTO[]::new)
        //     );
        //     return dto;
        // }).toList();

        // Using mappers
        List<ProjectOutDTO> projectOutDTOsMapper = projects.stream()
                .map(project -> projectMapper.toProjectOutDTO(project))
                .toList();

        return projects.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(projectOutDTOsMapper);
    }

    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteById(id);
            return ResponseEntity.status(200).body("Project with id " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
