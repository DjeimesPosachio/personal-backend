package com.personal.dtos.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRequestDto {
    private Boolean treinoAtual;
    private String description;
    private LocalDate date;
    private Long WorkoutPlanId;
}
