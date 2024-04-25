package com.personal.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<WorkoutPlan> WorkoutPlan;

    public Training(TrainingRequestDto data) {
        this.date = data.date();
    }

}
