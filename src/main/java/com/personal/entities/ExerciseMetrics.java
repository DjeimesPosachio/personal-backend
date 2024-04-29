package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "exercisemetric_entity")
@Entity(name = "workoutplan_entity")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExerciseMetrics {
    public String id;

    @ManyToOne
    @JoinColumn(name = "exercicioId")

    private String sets;

    private String sequence;

    private float durationRest;

    @OneToMany(mappedBy = "id")
    private List<Exercise> exercise;
}
