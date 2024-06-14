package com.personal.entities;

import com.personal.enums.UnidadeMedida;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "itemRefeicao")
@Entity(name = "itemRefeicao")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemRefeicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    @Column(name = "unidade_medida", length = 30)
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "refeicaoId")
    private RefeicaoEntity refeicao;


}
