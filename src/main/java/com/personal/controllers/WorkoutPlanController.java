package com.personal.controllers;

import java.util.List;

import com.personal.dtos.request.WorkoutPlanRequestDto;
import com.personal.dtos.response.WorkoutPlanResponseDto;

import com.personal.services.WorkoutPlanService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plano de Treino", description = "Endpoints de exemplo")
@RestController
@RequestMapping("/v1/workoutplan")
public class WorkoutPlanController {
    @Autowired
    private WorkoutPlanService service;

    @PostMapping
    public ResponseEntity<WorkoutPlanResponseDto> Create(@RequestBody WorkoutPlanRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutPlanResponseDto>> GetAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlanResponseDto> update(
            @PathVariable Long id,
            @RequestBody @Valid WorkoutPlanRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
