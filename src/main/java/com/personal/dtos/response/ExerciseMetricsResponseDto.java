package com.personal.dtos.response;

import com.personal.entities.ExerciseMetrics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseMetricsResponseDto {
    private Long id;

    private Long sets;

    private Long sequence;

    private Float durationRest;

    public ExerciseMetricsResponseDto(ExerciseMetrics metrics) {
        this(
                metrics.getId(),
                metrics.getSets(),
                metrics.getSequence(),
                metrics.getDurationRest());
    }
}
