package com.personal.dtos.response;

import java.util.List;
import java.util.stream.Collectors;

import com.personal.entities.User;
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
    private String name;
    private String email;

    public UsuarioResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getNome();
        this.email = user.getEmail();
    }
}
