package com.example.demo1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TaskAbstractDTO {
    
    private Long id;
    private String name;
}
