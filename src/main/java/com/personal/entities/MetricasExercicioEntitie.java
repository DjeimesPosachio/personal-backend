package com.personal.entities;

import com.personal.dtos.request.MetricasExerciciosRequestDto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "metricasExercicio")
@Entity(name = "metricasExercicio")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetricasExercicioEntitie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long series;

    private Long repeticoes;

    private Float tempoDescanso;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "exercicioId")
    private ExercicioEntitie exercicioEntitie;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    private TreinoEntitie treinoEntitie;

    public MetricasExercicioEntitie(MetricasExerciciosRequestDto dto) {
        this.series = dto.getSeries();
        this.repeticoes = dto.getRepeticoes();
        this.tempoDescanso = dto.getTempoDescanso();
        this.observacao = dto.getObservacao();
    }

}