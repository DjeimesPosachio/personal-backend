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

  private String descricao;

  @JoinColumn(name = "planejamentoTreinoId", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private PlanejamentoTreinoEntity planejamentoTreinoEntity;

  @OneToMany(mappedBy = "treinoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MetricasExercicioEntity> metricasExercicio;

  public TreinoEntity(TreinoRequestDto dto) {
    this.treinoAtual = dto.getTreinoAtual();
    this.descricao = dto.getDescricao();
  }
}