package com.personal.dtos.response;

import com.personal.entities.AlunoEntity;
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

    public AlunoResponseDto(AlunoEntity aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.dataNascimento = aluno.getDataNascimento();
        this.usuario = new UsuarioResponseDto(aluno.getUser());
    }
}
