package com.personal.dtos.response;

import com.personal.entities.Training;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingResponseDto {
    private Long id;
    private Boolean treinoAtual;
    private String description;
    private LocalDate date;
    private List<ExerciseMetricsResponseDto> exerciseMetrics;

    public TrainingResponseDto(Training training) {

        this.id = training.getId();
        this.treinoAtual = training.getTreinoAtual();
        this.description = training.getDescription();
        this.exerciseMetrics = training.getExerciseMetrics() != null ? training.getExerciseMetrics().stream()
                .map(ExerciseMetricsResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }
}
