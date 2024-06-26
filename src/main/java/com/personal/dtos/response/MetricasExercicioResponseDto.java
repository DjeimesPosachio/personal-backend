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
    private Long series;
    private Long repeticoes;
    private Float tempoDescanso;
    private String observacao;
    private ExercicioResponseDto exercicio;

    public MetricasExercicioResponseDto(MetricasExercicioEntity metricas) {
        this.id = metricas.getId();
        this.series = metricas.getSeries();
        this.repeticoes = metricas.getRepeticoes();
        this.tempoDescanso = metricas.getTempoDescanso();
        this.observacao = metricas.getObservacao();
        this.exercicio = new ExercicioResponseDto(metricas.getExercicio());
    }
}
