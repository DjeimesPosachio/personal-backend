package com.personal.dtos.response;

import com.personal.entities.ExercicioEntitie;
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

    public ExercicioResponseDto(ExercicioEntitie exercicioEntitie) {
        this(
                exercicioEntitie.getId(),
                exercicioEntitie.getFileName(),
                exercicioEntitie.getNomeExercicio(),
                exercicioEntitie.getSeries(),
                exercicioEntitie.getRepeticoes(),
                exercicioEntitie.getUrlGif());
    }

}