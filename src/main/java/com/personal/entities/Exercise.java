package com.personal.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "exercise_entity")
@Entity(name = "exercise_entity")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    private String UrlGif;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<WorkoutPlan> workoutPlans;
}
