package com.personal.entities;

import com.personal.dtos.request.PlanejamentoDietaRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "planejamentoDieta")
@Entity(name = "planejamentoDieta")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PlanejamentoDietaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicial_dieta", nullable = false)
    private LocalDate dataInicialDieta;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_final_dieta", nullable = false)
    private LocalDate dataFinalDieta;

    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AlunoEntity aluno;

    @OneToMany(mappedBy = "planejamentoDieta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefeicaoEntity> refeicoes;

    public PlanejamentoDietaEntity(PlanejamentoDietaRequestDto dto) {
        this.dataInicialDieta = dto.getDataInicialDieta();
        this.dataFinalDieta = dto.getDataFinalDieta();
    }
}
