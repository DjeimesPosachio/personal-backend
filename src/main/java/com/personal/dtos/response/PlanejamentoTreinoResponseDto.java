package com.personal.dtos.response;

import java.time.LocalDate;

import com.personal.entities.PlanejamentoTreinoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoTreinoResponseDto {

    private Long id;
    private LocalDate dataInicialPlano;
    private LocalDate dataFinalPlano;

    public PlanejamentoTreinoResponseDto(PlanejamentoTreinoEntity plan) {
        this.id = plan.getId();
        this.dataInicialPlano = plan.getDataInicialPlano();
        this.dataFinalPlano = plan.getDataFinalPlano();
    }

}
