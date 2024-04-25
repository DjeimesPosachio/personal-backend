package com.personal.dtos.response;

import java.util.List;

import com.personal.entities.User;

public record UserResponseDto(String id, String name, List<TrainingResponseDto> trainings) {
    public UserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getTrainings().stream().map(TrainingResponseDto::new).toList());
    }
}
