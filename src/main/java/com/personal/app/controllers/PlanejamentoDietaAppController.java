package com.personal.app.controllers;

import com.personal.app.services.PlanejamentoDietaAppService;
import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plano de dieta app", description = "Planejamento de dieta para uso do aplicativo")
@RestController
@RequestMapping("/v1/app/planejamento-dieta")
@RequiredArgsConstructor
public class PlanejamentoDietaAppController {

    private final PlanejamentoDietaAppService planejamentoDietaAppService;

    @GetMapping
    public ResponseEntity<PlanejamentoDietaResponseDto> recuperarPlanejamentoUsuarioLogado() {
        return ResponseEntity.ok(planejamentoDietaAppService.recuperarPlanejamentoDietaUsuarioLogado());
    }
}
