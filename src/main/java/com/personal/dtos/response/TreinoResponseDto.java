package com.personal.dtos.response;

import com.personal.entities.TreinoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreinoResponseDto {
    private Long id;
    private Boolean treinoAtual;
    private String descricao;
    private String sequenciaTreino;
    private List<MetricasExercicioResponseDto> metricasExercicio;

    public TreinoResponseDto(TreinoEntity treinoEntity) {

        this.id = treinoEntity.getId();
        this.treinoAtual = treinoEntity.getTreinoAtual();
        this.descricao = treinoEntity.getDescricao();
        this.sequenciaTreino = treinoEntity.getSequenciaTreino();
        this.metricasExercicio = treinoEntity.getMetricasExercicio() != null ? treinoEntity.getMetricasExercicio().stream()
                .map(MetricasExercicioResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }
}
