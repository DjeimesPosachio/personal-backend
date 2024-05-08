package com.personal.dtos.response;

import java.time.LocalDate;

import com.personal.entities.WorkoutPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanResponseDto {

    private Long id;
    private LocalDate dataInicialPlano;
    private LocalDate dataFinalPlano;

    public WorkoutPlanResponseDto(WorkoutPlan plan) {
        this.id = plan.getId();
        this.dataInicialPlano = plan.getDataInicialPlano();
        this.dataFinalPlano = plan.getDataFinalPlano();
    }

}
