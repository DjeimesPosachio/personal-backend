package com.personal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;
}