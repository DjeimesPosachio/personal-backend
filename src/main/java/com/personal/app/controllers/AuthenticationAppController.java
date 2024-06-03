package com.personal.app.controllers;

import com.personal.app.services.AutheticationAppService;
import com.personal.dtos.request.AuthenticationRequestDto;
import com.personal.dtos.response.LoginResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação app", description = "Autenticação do aplicativo")
@RestController
@RequestMapping("/v1/app/auth")
@RequiredArgsConstructor
public class AuthenticationAppController {

    private final AutheticationAppService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginApp(@RequestBody @Valid AuthenticationRequestDto data) {
        return ResponseEntity.ok(service.Login(data));
    }
}
