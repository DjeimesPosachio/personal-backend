package com.personal.app.controllers;

import com.personal.app.services.PerfilUsuarioService;
import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Perfil de usuário app", description = "Perfil de usuário do aplicativo")
@RestController
@RequestMapping("/v1/app/perfil")
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    @GetMapping
    public ResponseEntity<PlanejamentoDietaResponseDto> findByLoggedUser() {
        return ResponseEntity.ok(perfilUsuarioService.findByLoggedUser());
    }

    @PutMapping("/editar")
    public ResponseEntity<PlanejamentoDietaResponseDto> updateProfile() {
        return ResponseEntity.ok(perfilUsuarioService.update());
    }
}
