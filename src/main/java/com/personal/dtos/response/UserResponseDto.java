package com.personal.dtos.response;

import java.util.List;
import java.util.stream.Collectors;

import com.personal.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<WorkoutPlanResponseDto> workoutplans;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.workoutplans = user.getWorkoutPlans() != null ? user.getWorkoutPlans().stream()
                .map(WorkoutPlanResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }
}
