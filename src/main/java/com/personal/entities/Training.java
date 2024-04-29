package com.personal.entities;

import java.util.Date;
import java.util.List;

import com.personal.dtos.request.TrainingRequestDto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "training")
@Entity(name = "training")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean treinoAtual;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "training")
    private List<ExerciseMetrics> exerciseMetrics;

    @JoinColumn(name = "workoutplanId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkoutPlan workoutPlan;

    public Training(TrainingRequestDto data) {
        this.date = data.getDate();
    }
}
