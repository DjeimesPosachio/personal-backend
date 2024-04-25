package com.personal.controllers;

import com.personal.dtos.request.AuthenticationEmailRequestDto;
import com.personal.dtos.request.AuthenticationPhoneRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.dtos.request.RegisterRequestDto;
import com.personal.dtos.response.LoginResponseDto;
import com.personal.entities.User;
import com.personal.repositories.IUserRepository;
import com.personal.segurity.TokenService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Auth", description = "Endpoints de exemplo")
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository repository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login/email")
    public ResponseEntity loginByEmail(@RequestBody @Valid AuthenticationEmailRequestDto data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/login/phone")
    public ResponseEntity loginByPhone(@RequestBody @Valid AuthenticationPhoneRequestDto data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.phone(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDto data) {
        if (this.repository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
