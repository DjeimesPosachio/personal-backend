package com.personal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoRefeicao {
    CAFE_MANHA("Café da manhã"),
    LANCHE_DA_MANHA("Lanche da manhã"),
    ALMOCO("Almoço"),
    CAFE_TARDE("Café da tarde"),
    JANTAR("Jantar");

    private final String descricao;
}
