package com.personal.dtos.response;

import com.personal.entities.RefeicaoEntity;
import com.personal.entities.TreinoEntity;
import com.personal.enums.TipoRefeicao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public RefeicaoResponseDto(RefeicaoEntity refeicao) {

        this.id = refeicao.getId();
        this.descricao = refeicao.getDescricao();
        this.descricao = refeicao.getDescricao();
        this.horaRefeicao = refeicao.getHoraRefeicao();
    }
}
