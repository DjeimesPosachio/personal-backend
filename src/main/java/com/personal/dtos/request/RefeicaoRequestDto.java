package com.personal.dtos.request;

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
public class RefeicaoRequestDto {
    private Long id;
    private String descricao;
    private LocalTime horaRefeicao;
    private TipoRefeicao tipoRefeicao;
}
