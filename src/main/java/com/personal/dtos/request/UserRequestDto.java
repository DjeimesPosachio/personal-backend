package com.personal.dtos.request;

import com.personal.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private UserRole role;
}
