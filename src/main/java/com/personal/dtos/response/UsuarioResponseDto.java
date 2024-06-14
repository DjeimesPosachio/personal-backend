package com.personal.dtos.response;

import com.personal.entities.User;
import com.personal.enums.UserRole;
import com.personal.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String email;
    private UserRole role;
    private UserStatus status;
    public UsuarioResponseDto(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.status = user.getStatus();
    }
}
