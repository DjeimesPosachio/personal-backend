package com.personal.dtos.response;

import com.personal.entities.Training;

public record TrainingResponseDto(String jsonList) {

    public TrainingResponseDto(Training training) {
        this(training.getJsonList());
    }
}
