package com.personal.dtos.response;

import com.personal.entities.ItemRefeicaoEntity;
import com.personal.enums.UnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRefeicaoResponseDto {

    private Long id;

    private String descricao;

    private Long quantidade;

    private UnidadeMedida unidadeMedida;

    private Integer unidadeCaseira;

    public ItemRefeicaoResponseDto(ItemRefeicaoEntity itemRefeicao) {
        this.id = itemRefeicao.getId();
        this.descricao = itemRefeicao.getDescricao();
        this.quantidade = itemRefeicao.getQuantidade();
        this.unidadeCaseira = itemRefeicao.getUnidadeCaseira();
        this.unidadeMedida = itemRefeicao.getUnidadeMedida();
    }
}
