package com.personal.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.dtos.request.AuthenticationRequestDto;
import com.personal.dtos.request.RegisterRequestDto;
import com.personal.dtos.response.LoginResponseDto;

import com.personal.services.AuthenticationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Auth")
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

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
