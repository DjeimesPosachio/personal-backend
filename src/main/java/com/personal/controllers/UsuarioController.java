package com.personal.controllers;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.AlunoResponseDto;
import com.personal.dtos.response.UsuarioResponseDto;
import com.personal.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuario")
@RestController
@RequestMapping("/v1/user")

public class UsuarioController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> CreateUser(@RequestBody @Valid UserRequestDto data) {
        return ResponseEntity.ok(service.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    public Page<UsuarioResponseDto> buscarUsuarios(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        return service.buscarUsuarios(PageRequest.of(page, size));
    }
}
