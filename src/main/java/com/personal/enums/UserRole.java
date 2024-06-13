package com.personal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("Admin"),
    USUARIO("Usuário"),
    ALUNO("Aluno");

    private final String descricao;
}