package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.personal.dtos.request.PlanejamentoTreinoRequestDto;

@Table(name = "planejamentoTreino")
@Entity(name = "planejamentoTreino")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PlanejamentoTreinoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate dataInicialPlano;

    @Temporal(TemporalType.DATE)
    private LocalDate dataFinalPlano;

    @ManyToOne
    @JoinColumn(name = "alunoId")
    private AlunoEntity aluno;

    @OneToMany(mappedBy = "planejamentoTreinoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreinoEntity> treinoEntities;

    public PlanejamentoTreinoEntity(PlanejamentoTreinoRequestDto dto) {
        this.dataInicialPlano = dto.getDataInicialPlano();
        this.dataFinalPlano = dto.getDataFinalPlano();
    }
}