package com.personal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlterarSenhaRequestDto {

    private String senhaAtual;
    private String senha;
    private String novaSenha;
}
