package com.flowforge.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public class ProcessDefinition {

    private String id;

    @NotBlank
    private String name;

    @NotEmpty
    private List<ProcessStep> steps;

    public ProcessDefinition() {
    }

    public ProcessDefinition(String name, List<ProcessStep> steps) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProcessStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ProcessStep> steps) {
        this.steps = steps;
    }
}
