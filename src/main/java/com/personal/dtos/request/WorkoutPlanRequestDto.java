package com.personal.dtos.request;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanRequestDto {
    private LocalDate dataInicialPlano;
    private LocalDate dataFinalPlano;
    private Long userId;
    private List<TrainingRequestDto> trainings;
}
