package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.personal.dtos.request.WorkoutPlanRequestDto;

@Table(name = "workoutplan")
@Entity(name = "workoutplan")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate dataInicialPlano;

    @Temporal(TemporalType.DATE)
    private LocalDate dataFinalPlano;

    @OneToMany(mappedBy = "workoutPlan")
    private List<Training> training;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public WorkoutPlan(WorkoutPlanRequestDto dto) {
        this.dataInicialPlano = dto.getDataInicialPlano();
        this.dataFinalPlano = dto.getDataFinalPlano();
    }
}