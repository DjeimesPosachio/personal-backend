package com.personal.services;

import com.personal.dtos.response.UsuarioResponseDto;
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
import com.personal.repositories.UsuarioRepository;
import com.personal.segurity.TokenService;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDto Login(AuthenticationRequestDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        UsuarioResponseDto userResponseDto = UsuarioResponseDto.builder()
                .id(((User) auth.getPrincipal()).getId())
                .email(((User) auth.getPrincipal()).getEmail())
                .name(((User) auth.getPrincipal()).getNome())
                .build();

        return new LoginResponseDto(token, userResponseDto);
    }

    public void Register(RegisterRequestDto dto) {
        if (this.repository.buscarPorId(dto.getId()).isPresent()) {
            throw new EventNotFoundException("Mensagem tESTE");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getEmail(), encryptedPassword, dto.getRole());
        this.repository.save(newUser);
    }
}
