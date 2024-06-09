package com.personal.dtos.response;

import com.personal.entities.AlunoEntity;
import com.personal.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private UsuarioResponseDto usuario;
    private Boolean existeDietaAtual;
    private Boolean existeTreinoAtual;

    public AlunoResponseDto(AlunoEntity aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.dataNascimento = aluno.getDataNascimento();
        this.usuario = new UsuarioResponseDto(aluno.getUser());
    }

    public AlunoResponseDto(Long id, String nome, LocalDate dataNascimento, User user, Boolean existeDietaAtual, Boolean existeTreinoAtual) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.usuario = new UsuarioResponseDto(user);
        this.existeDietaAtual = existeDietaAtual;
        this.existeTreinoAtual = existeTreinoAtual;
    }
}
