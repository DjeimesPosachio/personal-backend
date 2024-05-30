package com.personal.controllers;

import com.personal.dtos.request.ExercicioRequestDto;
import com.personal.dtos.response.ExercicioResponseDto;
import com.personal.dtos.response.UsuarioResponseDto;
import com.personal.services.ExercicioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Exercicio", description = "Criacao de exercicio")
@RestController
@RequestMapping("/v1/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService service;

    @PostMapping
    public ExercicioResponseDto create(@RequestBody ExercicioRequestDto dto) {
        return service.create(dto);
    }

    @Async
    @GetMapping("allAsync")
    public CompletableFuture<List<ExercicioResponseDto>> findAllAsync() throws InterruptedException {
        return service.findAllAsync();
    }

    @GetMapping("/{id}")
    public ExercicioResponseDto findById(@PathVariable Long id) {
        return service.recuperarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponseDto> update(@PathVariable Long id, @RequestBody ExercicioRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping
    public Page<ExercicioResponseDto> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        return service.findAll(PageRequest.of(page, size));
    }

}