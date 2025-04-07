package com.example.demo1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.demo1.dto.Projects.ProjectOutDTO;
import com.example.demo1.model.Project;

@Mapper(componentModel = "spring", uses = {TaskMapper.class}) 
public interface ProjectMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "nombreCambiado"),
        @Mapping(source = "tasks", target = "tasks"),
    })
    ProjectOutDTO toProjectOutDTO(Project project);
}
