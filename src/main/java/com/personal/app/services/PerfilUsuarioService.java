package com.personal.app.services;

import com.personal.dtos.request.AlterarPerfilRequestDto;
import com.personal.dtos.request.AlterarSenhaRequestDto;
import com.personal.dtos.response.AlunoResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.User;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.UsuarioRepository;
import com.personal.security.UsuarioLogado;
import com.personal.validators.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PerfilUsuarioService {

    private final AlunoRepository alunoRepository;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public AlunoResponseDto findByLoggedUser() {

        AlunoEntity aluno = alunoRepository.findByUserId(UsuarioLogado.getIdUsuarioLogado()).orElseThrow(
                () -> new EventNotFoundException("Perfil não encontrado"));

        return new AlunoResponseDto(aluno);
    }

    public AlunoResponseDto update(AlterarPerfilRequestDto request) {

        AlunoEntity aluno = alunoRepository.findByUserId(UsuarioLogado.getIdUsuarioLogado()).orElseThrow(
                () -> new EventNotFoundException("Perfil não encontrado"));

        aluno.setDataNascimento(Objects.nonNull(request.getDataNascimento()) ? request.getDataNascimento() : null);

        alunoRepository.save(aluno);

        return new AlunoResponseDto(aluno);

    }

    public void updatePassword(AlterarSenhaRequestDto request) {

        PasswordValidator.compararSenhas(request.getSenha(), request.getNovaSenha());

        User user = UsuarioLogado.getUsuarioLogado();

        if (!passwordEncoder.matches(request.getSenhaAtual(), user.getSenha()))
            throw new EventNotFoundException("Senha atual está incorreta");


        usuarioRepository.save(user);

    }

}
