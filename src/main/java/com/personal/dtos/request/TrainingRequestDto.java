package com.personal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRequestDto {
    private Long id;
    private Boolean treinoAtual;
    private String description;
    private List<ExerciseMetricsRequestDto> exerciseMetrics;
}
