package com.personal.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioRequestDto {
    @NotBlank(message = "description cannot be blank")
    private String nomeExercicio;
    private Long series;
    private Long repeticoes;
}
