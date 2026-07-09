package com.flowforge.service;

import com.flowforge.model.InstanceStatus;
import com.flowforge.model.ProcessDefinition;
import com.flowforge.model.ProcessInstance;
import com.flowforge.model.ProcessStep;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProcessService {

    private final Map<String, ProcessDefinition> definitions = new ConcurrentHashMap<>();
    private final Map<String, ProcessInstance> instances = new ConcurrentHashMap<>();

    public ProcessDefinition createDefinition(ProcessDefinition definition) {
        ProcessDefinition saved = new ProcessDefinition(definition.getName(), definition.getSteps());
        definitions.put(saved.getId(), saved);
        return saved;
    }

    public ProcessDefinition getDefinition(String id) {
        ProcessDefinition def = definitions.get(id);
        if (def == null) {
            throw new NoSuchElementException("No process definition found with id " + id);
        }
        return def;
    }

    public ProcessInstance startInstance(String definitionId) {
        // Validate the definition exists before starting an instance of it
        getDefinition(definitionId);
        ProcessInstance instance = new ProcessInstance(definitionId);
        instances.put(instance.getId(), instance);
        return instance;
    }

    public ProcessInstance getInstance(String instanceId) {
        ProcessInstance instance = instances.get(instanceId);
        if (instance == null) {
            throw new NoSuchElementException("No process instance found with id " + instanceId);
        }
        return instance;
    }

    public ProcessInstance completeCurrentStep(String instanceId) {
        ProcessInstance instance = getInstance(instanceId);
        ProcessDefinition definition = getDefinition(instance.getProcessDefinitionId());

        if (instance.getStatus() == InstanceStatus.COMPLETED) {
            return instance;
        }

        int nextIndex = instance.getCurrentStepIndex() + 1;
        if (nextIndex >= definition.getSteps().size()) {
            instance.setCurrentStepIndex(definition.getSteps().size() - 1);
            instance.setStatus(InstanceStatus.COMPLETED);
        } else {
            instance.setCurrentStepIndex(nextIndex);
        }
        return instance;
    }

    public ProcessStep getCurrentStep(ProcessInstance instance) {
        ProcessDefinition definition = getDefinition(instance.getProcessDefinitionId());
        return definition.getSteps().get(instance.getCurrentStepIndex());
    }
}
