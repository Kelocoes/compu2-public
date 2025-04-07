package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo1.model.Project;
import com.example.demo1.service.IProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    
    @GetMapping
    public String getAllProjects(Model model) {
        model.addAttribute("title", "Listado de proyectos");
        model.addAttribute("projects", projectService.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(authority -> {
            System.out.println("Authority: " + authority.getAuthority());
        });
        return "projects/projectsList";
    }

    @GetMapping("/{id}")
    public String getProject(Model model, @PathVariable String id) {
        model.addAttribute("title", "El titulo del project");
        projectService.findById(Long.parseLong(id)).ifPresent(project -> model.addAttribute("project", project));
        return "projects/project";
    }

    @PostMapping("/create")
    public String saveProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/projects";
    }

    @PostMapping("/findByName")
    public String findByName(@RequestParam String name, Model model) {
        model.addAttribute("projects", projectService.findByNameParam(name));
        return "projects/projectsList";
    }

}
