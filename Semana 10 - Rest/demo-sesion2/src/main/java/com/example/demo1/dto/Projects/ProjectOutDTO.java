package com.example.demo1.dto.Projects;

import com.example.demo1.dto.ProjectAbstractDTO;
import com.example.demo1.dto.Tasks.TaskOutDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectOutDTO extends ProjectAbstractDTO {
    
    private TaskOutDTO[] tasks;
}
