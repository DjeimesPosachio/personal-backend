package com.personal.controllers;

import com.personal.dtos.response.ExerciseResponseDto;
import com.personal.entities.Exercise;
import com.personal.repositories.IExerciseRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Exercicio", description = "Criacao de exercicio")
@RestController
@RequestMapping("exercise")
public class ExerciseController {

    @Autowired
    private IExerciseRepository repository;

    @PostMapping
    public String create() {
        return "create";
    }

    @GetMapping("all")
    public List<ExerciseResponseDto> getAll() {
        return repository.findAll().stream().map(ExerciseResponseDto::new).toList();
    }

    @GetMapping("/{id}")
    public ExerciseResponseDto findById(@PathVariable String id) {
        Optional<Exercise> exercise = repository.findById(id);
        return new ExerciseResponseDto(exercise.get());
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return id;
    }

    @PutMapping
    public String update() {
        return "update";
    }

}