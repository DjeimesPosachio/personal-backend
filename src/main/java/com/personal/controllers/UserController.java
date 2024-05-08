package com.personal.controllers;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UserResponseDto;
import com.personal.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuario")
@RestController
@RequestMapping("/v1/user")

public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDto> CreateUser(@RequestBody @Valid UserRequestDto data) {
        return ResponseEntity.ok(service.create(data));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> GetAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
