package com.personal.controllers;

import com.personal.dtos.request.PlanejamentoTreinoRequestDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.services.PlanejamentoTreinoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plano de Treino", description = "Endpoints de exemplo")
@RestController
@RequestMapping("/v1/workoutplan")
public class PlanejamentoTreinoController {
    @Autowired
    private PlanejamentoTreinoService service;

    @PostMapping
    public void Create(@RequestBody PlanejamentoTreinoRequestDto dto) {
        service.create(dto);
    }

    @GetMapping
    public ResponseEntity<List<PlanejamentoTreinoResponseDto>> GetAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoTreinoResponseDto> recuperarPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(service.recuperarPlanejamentoPeloId(id));
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody @Valid PlanejamentoTreinoRequestDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
