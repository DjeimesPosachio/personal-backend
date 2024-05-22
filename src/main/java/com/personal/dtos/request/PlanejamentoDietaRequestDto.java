package com.personal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoDietaRequestDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicialDieta;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFinalDieta;
    private Long alunoId;
    private List<RefeicaoRequestDto> refeicoes;
}
