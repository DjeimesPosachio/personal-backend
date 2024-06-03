package com.personal.controllers;

import com.personal.dtos.request.AuthenticationRequestDto;
import com.personal.dtos.request.RegisterRequestDto;
import com.personal.dtos.response.LoginResponseDto;
import com.personal.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationRequestDto data) {
        return ResponseEntity.ok(service.Login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRequestDto data) {
        service.Register(data);
        return ResponseEntity.ok().build();
    }

}
