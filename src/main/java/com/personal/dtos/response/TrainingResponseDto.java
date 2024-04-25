package com.personal.dtos.response;

import java.util.Date;

import com.personal.entities.Training;

public record TrainingResponseDto(String id, Date date, String idUser) {

    public TrainingResponseDto(Training training) {

        this(training.getId(), training.getDate(), training.getUser().getId());
    }
}
