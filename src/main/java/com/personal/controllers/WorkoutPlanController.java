package com.personal.controllers;

import com.personal.dtos.request.WorkoutPlanRequestDto;
import com.personal.dtos.response.WorkoutPlanResponseDto;
import com.personal.services.WorkoutPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plano de Treino", description = "Endpoints de exemplo")
@RestController
@RequestMapping("/v1/workoutplan")
public class WorkoutPlanController {
    @Autowired
    private WorkoutPlanService service;

    @PostMapping
    public void Create(@RequestBody WorkoutPlanRequestDto dto) {
        service.create(dto);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutPlanResponseDto>> GetAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody @Valid WorkoutPlanRequestDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
