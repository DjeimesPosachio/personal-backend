package com.personal.app.controllers;

import com.personal.app.services.PerfilUsuarioService;
import com.personal.dtos.request.AlterarPerfilRequestDto;
import com.personal.dtos.request.AlterarSenhaRequestDto;
import com.personal.dtos.response.AlunoResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Perfil de usuário app", description = "Perfil de usuário do aplicativo")
@RestController
@RequestMapping("/v1/app/perfil")
@RequiredArgsConstructor
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    @GetMapping
    public ResponseEntity<AlunoResponseDto> findByLoggedUser() {
        return ResponseEntity.ok(perfilUsuarioService.findByLoggedUser());
    }

    @PutMapping("/editar")
    public ResponseEntity<AlunoResponseDto> updateProfile(@RequestBody AlterarPerfilRequestDto request) {
        return ResponseEntity.ok(perfilUsuarioService.update(request));
    }

    @PutMapping("/atualizar-senha")
    public void updatePassword(@RequestBody AlterarSenhaRequestDto request) {
        perfilUsuarioService.updatePassword(request);
    }

}
