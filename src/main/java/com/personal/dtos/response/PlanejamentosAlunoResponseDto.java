package com.personal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentosAlunoResponseDto {

    private PlanejamentoTreinoResponseDto planejamentoTreino;
    private PlanejamentoDietaResponseDto planejamentoDieta;

}
