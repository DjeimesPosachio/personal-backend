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

    @Column(name = "series")
    private Long series;

    @Column(name = "repeticoes")
    private Long repeticoes;

    public ExercicioEntity(ExercicioRequestDto dto) {

        this.nomeExercicio = dto.getNomeExercicio();
        this.series = dto.getSeries();
        this.repeticoes = dto.getRepeticoes();

    }

    public ExercicioEntity(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
        this.series = 0L;
        this.repeticoes = 0L;
    }
}