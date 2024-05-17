package com.personal.dtos.response;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.personal.entities.PlanejamentoTreinoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoTreinoResponseDto {

    private Long id;
    private LocalDate dataInicialPlano;
    private LocalDate dataFinalPlano;
    private List<TreinoResponseDto> treinos;

    public PlanejamentoTreinoResponseDto(PlanejamentoTreinoEntity plan) {
        this.id = plan.getId();
        this.dataInicialPlano = plan.getDataInicialPlano();
        this.dataFinalPlano = plan.getDataFinalPlano();

        this.treinos =  !CollectionUtils.isEmpty(plan.getTreinoEntities()) ? plan.getTreinoEntities().stream()
                .map(TreinoResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }

}
