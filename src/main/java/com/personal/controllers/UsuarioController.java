package com.personal.controllers;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UsuarioResponseDto;
import com.personal.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public Page<UsuarioResponseDto> buscarUsuarios(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        return service.buscarUsuarios(PageRequest.of(page, size));
    }
}
