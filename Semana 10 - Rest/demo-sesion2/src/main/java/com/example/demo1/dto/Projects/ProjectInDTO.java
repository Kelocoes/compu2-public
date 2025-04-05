package com.example.demo1.dto.Projects;

import java.time.LocalDate;

import com.example.demo1.dto.ProjectAbstractDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectInDTO extends ProjectAbstractDTO{

    private LocalDate dateCreated;
}
