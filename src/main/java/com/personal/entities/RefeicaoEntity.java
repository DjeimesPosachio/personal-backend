package com.personal.entities;

import com.personal.enums.TipoRefeicao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Table(name = "refeicao")
@Entity(name = "refeicao")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RefeicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipoRefeicao")
    private TipoRefeicao tipoRefeicao;

    @Column(name = "horaRefeicao")
    private LocalTime horaRefeicao;

    @JoinColumn(name = "planejamentoDietaId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PlanejamentoDietaEntity planejamentoDieta;
}
