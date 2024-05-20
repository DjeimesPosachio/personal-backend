package com.personal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricasExerciciosRequestDto {
    private Long id;
    private Long series;
    private Long repeticoes;
    private Float tempoDescanso;
    private String observacao;
    private Long exercicioId;
    private Long treinoId;
}
