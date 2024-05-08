package com.personal.entities;

import java.time.LocalDate;
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

  private LocalDate date;

  @OneToMany(mappedBy = "training")
  private List<ExerciseMetrics> exerciseMetrics;

  @JoinColumn(name = "workoutplanId", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private WorkoutPlan workoutPlan;

  public Training(TrainingRequestDto dto) {
    this.treinoAtual = dto.getTreinoAtual();
    this.description = dto.getDescription();
    this.date = dto.getDate();
  }
}