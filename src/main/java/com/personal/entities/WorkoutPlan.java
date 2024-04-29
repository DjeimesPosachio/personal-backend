package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "workoutplan_entity")
@Entity(name = "workoutplan_entity")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
