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
    @Column(name = "data_inicial_treino", nullable = false)
    private LocalDate dataInicialPlano;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_final_treino", nullable = false)
    private LocalDate dataFinalPlano;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @OneToMany(mappedBy = "planejamentoTreino", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreinoEntity> treinos;

    public PlanejamentoTreinoEntity(PlanejamentoTreinoRequestDto dto) {
        this.dataInicialPlano = dto.getDataInicialPlano();
        this.dataFinalPlano = dto.getDataFinalPlano();
    }
}