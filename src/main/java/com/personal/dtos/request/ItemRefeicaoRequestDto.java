package com.personal.dtos.request;

import com.personal.enums.UnidadeMedida;
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

    private UnidadeMedida unidadeMedida;

    private Integer unidadeCaseira;

}
