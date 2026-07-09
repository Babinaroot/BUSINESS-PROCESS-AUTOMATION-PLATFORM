package com.flowforge.controller;

import com.flowforge.model.ProcessDefinition;
import com.flowforge.model.ProcessInstance;
import com.flowforge.service.ProcessService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @PostMapping("/processes")
    public ResponseEntity<ProcessDefinition> createProcess(@Valid @RequestBody ProcessDefinition definition) {
        ProcessDefinition saved = processService.createDefinition(definition);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/processes/{id}")
    public ResponseEntity<ProcessDefinition> getProcess(@PathVariable String id) {
        return ResponseEntity.ok(processService.getDefinition(id));
    }

    @PostMapping("/processes/{id}/start")
    public ResponseEntity<ProcessInstance> startProcess(@PathVariable String id) {
        ProcessInstance instance = processService.startInstance(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(instance);
    }

    @GetMapping("/instances/{id}")
    public ResponseEntity<ProcessInstanceResponse> getInstance(@PathVariable String id) {
        ProcessInstance instance = processService.getInstance(id);
        ProcessInstanceResponse response = new ProcessInstanceResponse(
                instance.getId(),
                instance.getProcessDefinitionId(),
                instance.getCurrentStepIndex(),
                instance.getStatus(),
                processService.getCurrentStep(instance)
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/instances/{id}/complete-current")
    public ResponseEntity<ProcessInstanceResponse> completeCurrentStep(@PathVariable String id) {
        ProcessInstance instance = processService.completeCurrentStep(id);
        ProcessInstanceResponse response = new ProcessInstanceResponse(
                instance.getId(),
                instance.getProcessDefinitionId(),
                instance.getCurrentStepIndex(),
                instance.getStatus(),
                processService.getCurrentStep(instance)
        );
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
