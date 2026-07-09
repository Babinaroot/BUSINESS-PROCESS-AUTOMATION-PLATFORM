package com.flowforge.model;

public class ProcessStep {

    private String name;
    private String assigneeRole;

    public ProcessStep() {
    }

    public ProcessStep(String name, String assigneeRole) {
        this.name = name;
        this.assigneeRole = assigneeRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssigneeRole() {
        return assigneeRole;
    }

    public void setAssigneeRole(String assigneeRole) {
        this.assigneeRole = assigneeRole;
    }
}
