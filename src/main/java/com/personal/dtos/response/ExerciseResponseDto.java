package com.personal.dtos.response;

import com.personal.entities.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponseDto {
    private Long id;
    private String name;
    private String description;
    private Long sets;
    private Long sequence;
    private String UrlGif;

    public ExerciseResponseDto(Exercise exercise) {
        this(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getSets(),
                exercise.getSequence(),
                exercise.getUrlGif());
    }

}