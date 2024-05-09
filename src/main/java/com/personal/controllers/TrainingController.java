package com.personal.controllers;

import com.personal.dtos.response.TrainingResponseDto;
import com.personal.services.TrainingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Treino", description = "Endpoints de exemplo")
@RestController
@RequestMapping("/v1/training")
public class TrainingController {
    @Autowired
    private TrainingService service;

    @GetMapping
    public ResponseEntity<List<TrainingResponseDto>> GetAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
