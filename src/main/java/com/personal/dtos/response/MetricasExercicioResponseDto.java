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
    private String nomeExercicio;

    public MetricasExercicioResponseDto(MetricasExercicioEntity metrics) {
        this.id = metrics.getId();
        this.series = metrics.getSeries();
        this.repeticoes = metrics.getRepeticoes();
        this.tempoDescanso = metrics.getTempoDescanso();
        this.nomeExercicio = metrics.getExercicioEntity().getNomeExercicio();
    }
}
