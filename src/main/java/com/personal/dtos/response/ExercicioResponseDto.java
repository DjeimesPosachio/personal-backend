package com.personal.dtos.response;

import com.personal.entities.ExercicioEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioResponseDto {
    private Long id;
    private String name;
    private Long sets;
    private Long sequence;

    public ExercicioResponseDto(ExercicioEntity exercicioEntity) {
        this(
                exercicioEntity.getId(),
                exercicioEntity.getNomeExercicio(),
                exercicioEntity.getSeries(),
                exercicioEntity.getRepeticoes());
    }

}