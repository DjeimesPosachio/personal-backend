package com.personal.dtos.response;

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
    private LocalDate dataNascimento;
    private UsuarioResponseDto usuario;
}
