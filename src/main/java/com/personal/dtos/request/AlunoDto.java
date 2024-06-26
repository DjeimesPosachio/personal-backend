package com.personal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Long usuarioId;
}

