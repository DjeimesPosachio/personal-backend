package com.personal.dtos.response;

import com.personal.entities.User;

public record UserResponseDto(String id, String name, String email) {
    public UserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
