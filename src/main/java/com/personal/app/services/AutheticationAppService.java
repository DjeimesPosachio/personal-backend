package com.personal.app.services;

import com.personal.dtos.request.AuthenticationRequestDto;
import com.personal.dtos.response.LoginResponseDto;
import com.personal.dtos.response.UsuarioResponseDto;
import com.personal.entities.User;
import com.personal.repositories.UsuarioRepository;
import com.personal.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutheticationAppService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public LoginResponseDto Login(AuthenticationRequestDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        UsuarioResponseDto userResponseDto = UsuarioResponseDto.builder()
                .id(((User) auth.getPrincipal()).getId())
                .email(((User) auth.getPrincipal()).getEmail())
                .nome(((User) auth.getPrincipal()).getNome())
                .build();

        return new LoginResponseDto(token, userResponseDto);
    }

}
