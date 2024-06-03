package com.personal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnidadeMedida {

    UNIDADE("unidade"),
    ML("ml"),
    GRAMAS("gr"),
    XICARA("xícara"),
    PORCAO("porção");

    private final String descricao;
}
