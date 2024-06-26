package com.personal.controllers;

import com.personal.dtos.response.PlanejamentosAlunoResponseDto;
import com.personal.services.PlanejamentosAlunoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Planejamentos", description = "Plenajamento de treino e dieta")
@RestController
@RequestMapping("/v1/planejamentos-aluno")
@RequiredArgsConstructor
public class PlanejamentosAlunoController {

    private final PlanejamentosAlunoService service;

    @GetMapping
    public PlanejamentosAlunoResponseDto findByAlunoId(@RequestParam(name = "idTreino", required = false) Long idTreino,
                                                       @RequestParam(name = "idDieta", required = false) Long idDieta) {
        return service.recuperarPlanejamentos(idTreino, idDieta);
    }
}
