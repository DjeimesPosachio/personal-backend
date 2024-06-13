package com.personal.entities;

import com.personal.dtos.request.ExercicioRequestDto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "exercicio")
@Entity(name = "exercicio")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExercicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_exercicio", length = 300, nullable = false)
    private String nomeExercicio;

    public ExercicioEntity(ExercicioRequestDto dto) {
        this.nomeExercicio = dto.getNomeExercicio();
    }

    public ExercicioEntity(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }
}