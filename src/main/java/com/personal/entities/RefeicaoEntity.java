package com.personal.entities;

import com.personal.enums.TipoRefeicao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

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

    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;

    @Column(name = "tipo_refeicao", length = 40, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoRefeicao tipoRefeicao;

    @Column(name = "hora_refeicao")
    private LocalTime horaRefeicao;

    @OneToMany(mappedBy = "refeicao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemRefeicaoEntity> itensRefeicao;

    @JoinColumn(name = "planejamento_dieta_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PlanejamentoDietaEntity planejamentoDieta;
}
