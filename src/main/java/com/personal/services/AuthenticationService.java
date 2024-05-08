package com.personal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.dtos.request.AuthenticationRequestDto;
import com.personal.dtos.request.RegisterRequestDto;
import com.personal.dtos.response.LoginResponseDto;
import com.personal.entities.User;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.IUserRepository;
import com.personal.segurity.TokenService;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository repository;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDto Login(AuthenticationRequestDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDto(token);
    }

    public void Register(RegisterRequestDto dto) {
        if (this.repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EventNotFoundException("Mensagem tESTE");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getEmail(), encryptedPassword, dto.getRole());
        this.repository.save(newUser);
    }
}
