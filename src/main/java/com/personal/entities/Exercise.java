package com.personal.entities;

import com.personal.dtos.request.ExerciseRequestDto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "exercise")
@Entity(name = "exercise")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long sets;

    private Long sequence;

    private String UrlGif;

    public Exercise(ExerciseRequestDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.sets = dto.getSets();
        this.sequence = dto.getSequence();

    }

    public Exercise(String name, String descricao, String urlGif) {
        this.name = name;
        this.description = descricao;
        this.sets = (long) 0;
        this.sequence = (long) 0;
        this.UrlGif = urlGif;
    }

}