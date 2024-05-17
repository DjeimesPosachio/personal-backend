package com.personal.entities;

import com.personal.dtos.request.MetricasExerciciosRequestDto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "metricasExercicio")
@Entity(name = "metricasExercicio")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetricasExercicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long series;

    private Long repeticoes;

    private Float tempoDescanso;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "exercicioId")
    private ExercicioEntity exercicioEntity;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    private TreinoEntity treinoEntity;

    public MetricasExercicioEntity(MetricasExerciciosRequestDto dto) {
        this.series = dto.getSeries();
        this.repeticoes = dto.getRepeticoes();
        this.tempoDescanso = dto.getTempoDescanso();
        this.observacao = dto.getObservacao();
    }

}