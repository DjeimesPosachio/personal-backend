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
public class WorkoutPlanRequestDto {
    private LocalDate dataInicialPlano;
    private LocalDate dataFinalPlano;
    private Long UserId;
}
