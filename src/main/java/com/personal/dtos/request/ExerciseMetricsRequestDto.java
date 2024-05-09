package com.personal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseMetricsRequestDto {
    private Long id;
    private Long sets;
    private Long sequence;
    private Float durationRest;
    private Long exerciseId;
    private Long trainingId;
}
