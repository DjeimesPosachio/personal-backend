package com.personal.dtos.request;

import com.personal.enums.EUnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRefeicaoRequestDto {

    private Long id;

    private String descricao;

    private Long quantidade;

    private EUnidadeMedida unidadeMedida;

    private Integer unidadeCaseira;

}
