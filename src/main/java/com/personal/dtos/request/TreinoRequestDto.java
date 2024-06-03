package com.personal.dtos.request;

import com.personal.enums.SequenciaTreino;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreinoRequestDto {
    private Long id;
    private Boolean treinoAtual;
    private String descricao;
    private SequenciaTreino sequenciaTreino;
    private List<MetricasExerciciosRequestDto> metricasExercicios;
}
