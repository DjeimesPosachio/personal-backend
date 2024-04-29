package com.personal.entities;

import java.util.Date;
import java.util.List;

import com.personal.dtos.request.TrainingRequestDto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "training_entity")
@Entity(name = "training_entity")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Boolean treinoAtual;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "id")
    private List<ExerciseMetrics> exerciseMetrics;

    @OneToMany(mappedBy = "id")
    private WorkoutPlan workoutPlan;

    public Training(TrainingRequestDto data) {
        this.date = data.date();
    }
}
