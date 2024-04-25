package com.personal.controllers;

import java.util.List;

import com.personal.dtos.request.TrainingRequestDto;
import com.personal.dtos.response.TrainingResponseDto;
import com.personal.entities.Training;
import com.personal.repositories.ITrainingRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Treino", description = "Endpoints de exemplo")
@RestController
@RequestMapping("training")

public class TrainingController {
    @Autowired
    private ITrainingRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Async
    public ResponseEntity<TrainingResponseDto> Create(@RequestBody TrainingRequestDto data) {
        Training temp = repository.save(new Training(data));
        return ResponseEntity.ok(new TrainingResponseDto(temp));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<TrainingResponseDto> GetAll() {
        return repository.findAll().stream().map(TrainingResponseDto::new).toList();
    }

    @DeleteMapping
    public String delete() {
        return "delete";
    }

    @PutMapping
    public String update() {
        return "update";
    }

}
