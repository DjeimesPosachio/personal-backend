package com.personal.dtos.response;

import com.personal.entities.Exercise;

public record ExerciseResponseDto(String id, String name, String urlGif) {
    public ExerciseResponseDto(Exercise exercise) {
        this(exercise.getId(), exercise.getName(), exercise.getUrlGif());
    }

}