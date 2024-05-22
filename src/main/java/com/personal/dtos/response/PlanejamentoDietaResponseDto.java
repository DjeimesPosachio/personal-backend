package com.personal.dtos.response;

import com.personal.entities.PlanejamentoDietaEntity;
import com.personal.entities.PlanejamentoTreinoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoDietaResponseDto {

    private Long id;
    private LocalDate dataInicialDieta;
    private LocalDate dataFinalDieta;
    private List<RefeicaoResponseDto> refeicoes;

    public PlanejamentoDietaResponseDto(PlanejamentoDietaEntity dieta) {
        this.id = dieta.getId();
        this.dataInicialDieta = dieta.getDataInicialDieta();
        this.dataFinalDieta = dieta.getDataFinalDieta();

        this.refeicoes =  !CollectionUtils.isEmpty(dieta.getRefeicoes()) ? dieta.getRefeicoes().stream()
                .map(RefeicaoResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }

}
