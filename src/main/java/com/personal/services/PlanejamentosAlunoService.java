package com.personal.services;

import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.dtos.response.PlanejamentosAlunoResponseDto;
import com.personal.entities.PlanejamentoDietaEntity;
import com.personal.entities.PlanejamentoTreinoEntity;
import com.personal.repositories.PlanejamentoDietaRepository;
import com.personal.repositories.PlanejamentoTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanejamentosAlunoService {

    private final PlanejamentoDietaRepository planejamentoDietaRepository;
    private final PlanejamentoTreinoRepository planejamentoTreinoRepository;

    public PlanejamentosAlunoResponseDto recuperarPlanejamentos(Long idTreino, Long idDieta) {

        Optional<PlanejamentoTreinoEntity> planejamentoTreino = Optional.empty();
        Optional<PlanejamentoDietaEntity> planejamentoDieta = Optional.empty();

        if (idTreino != null)
            planejamentoTreino = planejamentoTreinoRepository.findById(idTreino);

        if (idDieta != null)
            planejamentoDieta = planejamentoDietaRepository.findById(idDieta);

        return PlanejamentosAlunoResponseDto.builder()
                .planejamentoTreino(planejamentoTreino.map(PlanejamentoTreinoResponseDto::new).orElse(null))
                .planejamentoDieta(planejamentoDieta.map(PlanejamentoDietaResponseDto::new).orElse(null))
                .build();
    }
}
