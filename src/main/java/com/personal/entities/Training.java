package com.personal.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jsonList;

    public Training(TrainingRequestDto data) {
        this.jsonList = data.jsonList();
    }

}
