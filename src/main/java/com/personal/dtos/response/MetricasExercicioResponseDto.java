package com.personal.dtos.response;

import com.personal.entities.MetricasExercicioEntity;

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

    public MetricasExercicioResponseDto(MetricasExercicioEntity metrics) {
        this(
                metrics.getId(),
                metrics.getSeries(),
                metrics.getRepeticoes(),
                metrics.getTempoDescanso());
    }
}
