package com.personal.controllers;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UsuarioResponseDto;
import com.personal.enums.UserStatus;
import com.personal.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuario")
@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody @Valid UserRequestDto data) {
        return ResponseEntity.ok(service.create(data));
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto recuperarUsuario(@PathVariable Long id) {
        return service.recuperarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> editarUsuario(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PutMapping("/{id}/inativar")
    public void inativarUsuario(@PathVariable Long id) {
        service.inativar(id);
    }

    @PutMapping("/{id}/ativar")
    public void ativarUsuario(@PathVariable Long id) {
        service.ativar(id);
    }

    @GetMapping
    public Page<UsuarioResponseDto> buscarUsuarios(@RequestParam(name = "status", required = false) UserStatus status,
                                                   @RequestParam(name = "nome", required = false) String nome,
                                                   @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        return service.buscarUsuarios(nome, status, PageRequest.of(page, size));
    }
}
