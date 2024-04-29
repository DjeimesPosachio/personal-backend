package com.personal.dtos.response;

import java.util.Date;

import com.personal.entities.Training;

public record TrainingResponseDto(Long id, Date date, Long idUser) {

    public TrainingResponseDto(Training training) {

        this(training.getId(), training.getDate(), training.getId());
    }
}
