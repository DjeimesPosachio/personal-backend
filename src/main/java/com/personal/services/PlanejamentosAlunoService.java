package com.personal.services;

import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.dtos.response.PlanejamentosAlunoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanejamentosAlunoService {

    private final PlanejamentoTreinoService treinoService;
    private final PlanejamentoDietaService dietaService;

    public PlanejamentosAlunoResponseDto recuperarPlanejamentosPeloIdAluno(Long idAluno) {

        PlanejamentoTreinoResponseDto planejamentoTreino = treinoService.recuperarUltimoPlanejamentoPeloIdAluno(idAluno);
        PlanejamentoDietaResponseDto planejamentoDieta = dietaService.recuperarUltimoPlanejamentoPeloIdAluno(idAluno);

        return PlanejamentosAlunoResponseDto.builder()
                .planejamentoTreino(planejamentoTreino)
                .planejamentoDieta(planejamentoDieta)
                .build();
    }
}
