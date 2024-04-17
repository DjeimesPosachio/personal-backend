package com.personal.dtos.request;

import com.personal.enums.EUserRole;

public record RegisterRequestDto(String email, String password, EUserRole role) {

}
