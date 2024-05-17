package com.personal.dtos.response;

import com.personal.entities.TreinoEntity;
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
public class TreinoResponseDto {
    private Long id;
    private Boolean treinoAtual;
    private String description;
    private LocalDate date;
    private List<MetricasExercicioResponseDto> exerciseMetrics;

    public TreinoResponseDto(TreinoEntity treinoEntity) {

        this.id = treinoEntity.getId();
        this.treinoAtual = treinoEntity.getTreinoAtual();
        this.description = treinoEntity.getDescricao();
        this.exerciseMetrics = treinoEntity.getMetricasExercicio() != null ? treinoEntity.getMetricasExercicio().stream()
                .map(MetricasExercicioResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }
}
