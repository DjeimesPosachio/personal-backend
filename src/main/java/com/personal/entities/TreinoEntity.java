package com.personal.entities;

import com.personal.dtos.request.TreinoRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "treino")
@Entity(name = "treino")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TreinoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Boolean treinoAtual;
  private String sequenciaTreino;
  private String descricao;

  @JoinColumn(name = "planejamento_treino_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private PlanejamentoTreinoEntity planejamentoTreino;

  @OneToMany(mappedBy = "treino", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MetricasExercicioEntity> metricasExercicio;

  public TreinoEntity(TreinoRequestDto dto) {
    this.treinoAtual = dto.getTreinoAtual();
    this.descricao = dto.getDescricao();
  }
}