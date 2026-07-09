package com.flowforge.model;

import java.util.UUID;

public class ProcessInstance {

    private String id;
    private String processDefinitionId;
    private int currentStepIndex;
    private InstanceStatus status;

    public ProcessInstance() {
    }

    public ProcessInstance(String processDefinitionId) {
        this.id = UUID.randomUUID().toString();
        this.processDefinitionId = processDefinitionId;
        this.currentStepIndex = 0;
        this.status = InstanceStatus.IN_PROGRESS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public int getCurrentStepIndex() {
        return currentStepIndex;
    }

    public void setCurrentStepIndex(int currentStepIndex) {
        this.currentStepIndex = currentStepIndex;
    }

    public InstanceStatus getStatus() {
        return status;
    }

    public void setStatus(InstanceStatus status) {
        this.status = status;
    }
}
