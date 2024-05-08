package com.personal.dtos.request;

import com.personal.enums.EUserRole;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "Email cannot be blank")
    private String email;

    private String password;
    private EUserRole role;
}
