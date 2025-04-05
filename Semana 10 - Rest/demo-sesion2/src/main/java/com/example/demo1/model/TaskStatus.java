package com.example.demo1.model;

public enum TaskStatus {
    TO_DO("To Do"), 
    IN_PROGRESS("In Progress"), 
    ON_HOLD("On Hold"), 
    DONE("Done");

    private final String label;

    private TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
