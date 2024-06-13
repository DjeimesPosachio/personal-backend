package com.personal.controllers;

import com.personal.dtos.request.PlanejamentoDietaRequestDto;
import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.services.PlanejamentoDietaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plano de dieta", description = "Plenajamento de dieta")
@RestController
@RequestMapping("/v1/planejamento-dieta")
@RequiredArgsConstructor
public class PlanejamentoDietaController {

    private final PlanejamentoDietaService service;

    @PostMapping
    public void Create(@RequestBody PlanejamentoDietaRequestDto dto) {
        service.create(dto);
    }

    @GetMapping
    public ResponseEntity<List<PlanejamentoDietaResponseDto>> GetAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoDietaResponseDto> recuperarPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(service.recuperarPlanejamentoPeloId(id));
    }

    @GetMapping("/recuperar-ultimo/{idAluno}")
    public ResponseEntity<PlanejamentoDietaResponseDto> recuperarUltimaDietaPeloIdAluno(@PathVariable Long idAluno) {
        return ResponseEntity.ok(service.recuperarUltimoPlanejamentoPeloIdAluno(idAluno));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody @Valid PlanejamentoDietaRequestDto dto, @PathVariable Long id) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
