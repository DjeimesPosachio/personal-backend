package com.personal.controllers;

import com.personal.dtos.request.ExerciseRequestDto;
import com.personal.dtos.response.ExerciseResponseDto;
import com.personal.services.ExerciseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Exercicio", description = "Criacao de exercicio")
@RestController
@RequestMapping("/v1/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService service;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ExerciseResponseDto create(@Valid ExerciseRequestDto dto, @RequestPart MultipartFile file) {
        return service.create(dto, file);
    }

    @GetMapping("all")
    public List<ExerciseResponseDto> findAll() {
        return service.findAll();
    }

    @Async
    @GetMapping("allAsync")
    public CompletableFuture<List<ExerciseResponseDto>> findAllAsync() throws InterruptedException {
        return service.findAllAsync();
    }

    @GetMapping("/{id}")
    public ExerciseResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> update(@PathVariable Long id, @RequestBody ExerciseRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.delete(id);
    }

}