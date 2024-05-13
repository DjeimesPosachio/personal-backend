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
public class TreinoEntitie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Boolean treinoAtual;

  private String descricao;

  @OneToMany(mappedBy = "treinoEntitie")
  private List<MetricasExercicioEntitie> metricasExercicio;

  @JoinColumn(name = "planejamentoTreinoId", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private PlanejamentoTreinoEntitie planejamentoTreinoEntitie;

  public TreinoEntitie(TreinoRequestDto dto) {
    this.treinoAtual = dto.getTreinoAtual();
    this.descricao = dto.getDescricao();
  }
}