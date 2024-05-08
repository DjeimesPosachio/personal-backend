package com.personal.controllers;

import com.personal.dtos.request.ExerciseMetricsRequestDto;
import com.personal.dtos.response.ExerciseMetricsResponseDto;
import com.personal.services.ExerciseMetricsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Metricas Exercicio", description = "Criacao de exercicio")
@RestController
@RequestMapping("/v1/exercisemetrics")
public class ExerciseMetricsController {

    @Autowired
    private ExerciseMetricsService service;

    @PostMapping
    public ResponseEntity<ExerciseMetricsResponseDto> create(@RequestBody @Valid ExerciseMetricsRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("all")
    public ResponseEntity<List<ExerciseMetricsResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Async
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseMetricsResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}