package com.personal.dtos.response;

import com.personal.entities.RefeicaoEntity;
import com.personal.enums.TipoRefeicao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefeicaoResponseDto {

    private Long id;

    private String descricao;

    private LocalTime horaRefeicao;

    private TipoRefeicao tipoRefeicao;

    private List<ItemRefeicaoResponseDto> itensRefeicao;

    public RefeicaoResponseDto(RefeicaoEntity refeicao) {
        this.id = refeicao.getId();
        this.descricao = refeicao.getDescricao();
        this.horaRefeicao = refeicao.getHoraRefeicao();
        this.tipoRefeicao = refeicao.getTipoRefeicao();

        this.itensRefeicao = !CollectionUtils.isEmpty(refeicao.getItensRefeicao()) ? refeicao.getItensRefeicao().stream()
                .map(ItemRefeicaoResponseDto::new)
                .collect(Collectors.toList())
                : List.of();
    }
}
