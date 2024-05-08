package com.personal.controllers;

import java.util.List;
import com.personal.dtos.request.TrainingRequestDto;
import com.personal.dtos.response.TrainingResponseDto;
import com.personal.services.TrainingService;

import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "Treino", description = "Endpoints de exemplo")
@RestController
@RequestMapping("/v1/training")
public class TrainingController {
    @Autowired
    private TrainingService service;

    @PostMapping
    public ResponseEntity<TrainingResponseDto> Create(@RequestBody TrainingRequestDto dto) {

        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TrainingResponseDto>> GetAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponseDto> update(@PathVariable Long id, @RequestBody TrainingRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
