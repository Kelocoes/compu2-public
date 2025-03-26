package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo1.model.Project;
import com.example.demo1.service.IProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/project")
public class ProjectController {
    
    @Autowired
    private IProjectService projectService;

    @GetMapping("/")    
    public String getAllProjects(Model model) {
        model.addAttribute("message", "Este texto viene del controlador");
        model.addAttribute("projects", projectService.findAll());
        return "projectList";
    }

    @GetMapping("/{id}")
    public String getProjectById(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.findById(id).get());
        return "projectDetail";
    }

    @PostMapping("/")
    public String saveProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/projects";
    }
    
    
}
