package com.personal.enums;

public enum UserRole {
    ADMIN("Admin"),
    USUARIO("Usuário"),
    ALUNO("Aluno");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}