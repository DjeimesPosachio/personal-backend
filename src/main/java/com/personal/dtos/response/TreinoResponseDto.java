package com.personal.dtos.response;

import com.personal.entities.TreinoEntity;
import com.personal.enums.SequenciaTreino;
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
    private SequenciaTreino sequenciaTreino;
    private List<MetricasExercicioResponseDto> metricasExercicios;

    public TreinoResponseDto(TreinoEntity treinoEntity) {

        this.id = treinoEntity.getId();
        this.treinoAtual = treinoEntity.getTreinoAtual();
        this.descricao = treinoEntity.getDescricao();
        this.sequenciaTreino = treinoEntity.getSequenciaTreino();
        this.metricasExercicios = treinoEntity.getMetricasExercicio() != null ? treinoEntity.getMetricasExercicio().stream()
                .map(MetricasExercicioResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }
}
