package com.personal.dtos.response;

import com.personal.entities.TreinoEntitie;
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

    public TreinoResponseDto(TreinoEntitie treinoEntitie) {

        this.id = treinoEntitie.getId();
        this.treinoAtual = treinoEntitie.getTreinoAtual();
        this.description = treinoEntitie.getDescricao();
        this.exerciseMetrics = treinoEntitie.getMetricasExercicio() != null ? treinoEntitie.getMetricasExercicio().stream()
                .map(MetricasExercicioResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }
}
