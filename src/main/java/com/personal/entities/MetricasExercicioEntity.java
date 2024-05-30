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
public class MetricasExercicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series", nullable = false)
    private Long series;

    @Column(name = "repeticoes", nullable = false)
    private Long repeticoes;

    @Column(name = "tempo_descanso")
    private Float tempoDescanso;

    @Column(name = "observacao", length = 400)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private ExercicioEntity exercicio;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private TreinoEntity treino;

    public MetricasExercicioEntity(MetricasExerciciosRequestDto dto) {
        this.series = dto.getSeries();
        this.repeticoes = dto.getRepeticoes();
        this.tempoDescanso = dto.getTempoDescanso();
        this.observacao = dto.getObservacao();
    }

}