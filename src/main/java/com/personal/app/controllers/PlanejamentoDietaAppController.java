package com.personal.app.controllers;

import com.personal.app.services.PlanejamentoDietaAppService;
import com.personal.app.services.PlanejamentoTreinoAppService;
import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plano de dieta app", description = "Planejamento de dieta para uso do aplicativo")
@RestController
@RequestMapping("/v1/app/planejamento-dieta")
public class PlanejamentoDietaAppController {

    @Autowired
    private PlanejamentoDietaAppService planejamentoDietaAppService;

    @GetMapping
    public ResponseEntity<PlanejamentoDietaResponseDto> recuperarPlanejamentoUsuarioLogado() {
        return ResponseEntity.ok(planejamentoDietaAppService.recuperarPlanejamentoDietaUsuarioLogado());
    }
}
