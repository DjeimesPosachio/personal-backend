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
    private String description;
    private Long sets;
    private Long sequence;
    private String UrlGif;

    public ExercicioResponseDto(ExercicioEntity exercicioEntity) {
        this(
                exercicioEntity.getId(),
                exercicioEntity.getFileName(),
                exercicioEntity.getNomeExercicio(),
                exercicioEntity.getSeries(),
                exercicioEntity.getRepeticoes(),
                exercicioEntity.getUrlGif());
    }

}