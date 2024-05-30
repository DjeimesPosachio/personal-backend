package com.personal.dtos.response;

import com.personal.entities.ItemRefeicaoEntity;
import com.personal.entities.RefeicaoEntity;
import com.personal.enums.EUnidadeMedida;
import com.personal.enums.TipoRefeicao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRefeicaoResponseDto {

    private Long id;

    private String descricao;

    private Long quantidade;

    private EUnidadeMedida unidadeMedida;

    private Integer unidadeCaseira;

    public ItemRefeicaoResponseDto(ItemRefeicaoEntity itemRefeicao) {
        this.id = itemRefeicao.getId();
        this.descricao = itemRefeicao.getDescricao();
        this.quantidade = itemRefeicao.getQuantidade();
        this.unidadeCaseira = itemRefeicao.getUnidadeCaseira();
        this.unidadeMedida = itemRefeicao.getUnidadeMedida();
    }
}
