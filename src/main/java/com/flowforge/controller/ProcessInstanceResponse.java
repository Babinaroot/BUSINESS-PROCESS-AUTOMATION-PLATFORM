package com.flowforge.controller;

import com.flowforge.model.InstanceStatus;
import com.flowforge.model.ProcessStep;

public class ProcessInstanceResponse {

    private String id;
    private String processDefinitionId;
    private int currentStepIndex;
    private InstanceStatus status;
    private ProcessStep currentStep;

    public ProcessInstanceResponse(String id, String processDefinitionId, int currentStepIndex,
                                    InstanceStatus status, ProcessStep currentStep) {
        this.id = id;
        this.processDefinitionId = processDefinitionId;
        this.currentStepIndex = currentStepIndex;
        this.status = status;
        this.currentStep = currentStep;
    }

    public String getId() {
        return id;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public int getCurrentStepIndex() {
        return currentStepIndex;
    }

    public InstanceStatus getStatus() {
        return status;
    }

    public ProcessStep getCurrentStep() {
        return currentStep;
    }
}
