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
public class ExercicioEntitie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String nomeExercicio;

    private Long series;

    private Long repeticoes;

    private String urlGif;

    public ExercicioEntitie(ExercicioRequestDto dto) {
        this.fileName = dto.getName();
        this.nomeExercicio = dto.getNomeExercicio();
        this.series = dto.getSeries();
        this.repeticoes = dto.getRepeticoes();

    }

    public ExercicioEntitie(String fileName, String nomeExercicio, String urlGif) {
        this.fileName = fileName;
        this.nomeExercicio = nomeExercicio;
        this.series = (long) 0;
        this.repeticoes = (long) 0;
        this.urlGif = urlGif;
    }
}