package com.personal.enums;

public enum EUserRole {
    ADMIN("Admin"),
    USUARIO("Usuário"),
    ALUNO("Aluno");

    private String role;

    EUserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}