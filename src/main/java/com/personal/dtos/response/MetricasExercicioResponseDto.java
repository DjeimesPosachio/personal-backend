package com.personal.dtos.response;

import com.personal.entities.MetricasExercicioEntitie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricasExercicioResponseDto {
    private Long id;

    private Long sets;

    private Long sequence;

    private Float durationRest;

    public MetricasExercicioResponseDto(MetricasExercicioEntitie metrics) {
        this(
                metrics.getId(),
                metrics.getSeries(),
                metrics.getRepeticoes(),
                metrics.getTempoDescanso());
    }
}
