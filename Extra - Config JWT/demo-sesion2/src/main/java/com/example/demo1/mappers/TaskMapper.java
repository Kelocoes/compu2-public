package com.example.demo1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.demo1.dto.Tasks.TaskOutDTO;
import com.example.demo1.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "dateCreated", target = "dateCreated"),
        @Mapping(source = "dueDate", target = "dueDate"),
        @Mapping(source = "status", target = "status")
    })
    TaskOutDTO toTaskOutDTO(Task task);

}