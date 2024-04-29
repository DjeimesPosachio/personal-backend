package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "exercisemetric")
@Entity(name = "exercisemetric")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExerciseMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercicioId")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    private Training training;

    private Long sets;

    private Long sequence;

    private Float durationRest;
}
