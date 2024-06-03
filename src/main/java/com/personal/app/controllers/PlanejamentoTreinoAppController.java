package com.personal.app.controllers;

import com.personal.app.services.PlanejamentoTreinoAppService;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plano de Treino app", description = "Planejamento de treino para uso do aplicativo")
@RestController
@RequestMapping("/v1/app/planejamento-treino")
@RequiredArgsConstructor
public class PlanejamentoTreinoAppController {

    private final PlanejamentoTreinoAppService planejamentoTreinoAppService;

    @GetMapping
    public ResponseEntity<PlanejamentoTreinoResponseDto> recuperarPlanejamentoUsuarioLogado() {
        return ResponseEntity.ok(planejamentoTreinoAppService.recuperarPlanejamentoTreinoUsuarioLogado());
    }
}
