package com.personal.services;

import com.personal.dtos.response.AlunoResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.PlanejamentoDietaRepository;
import com.personal.repositories.PlanejamentoTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PlanejamentoTreinoRepository planejamentoTreinoRepository;
    private final PlanejamentoDietaRepository planejamentoDietaRepository;

    public AlunoEntity findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Aluno não encontrado"));
    }

    public Page<AlunoResponseDto> findAll(Pageable pageable) {
        Page<AlunoEntity> alunos = alunoRepository.findAll(pageable);
        return alunos.map(a -> new AlunoResponseDto(
                        a.getId(),
                        a.getNome(),
                        a.getDataNascimento(),
                        a.getUser(),
                        planejamentoDietaRepository.existsCurrentDietaByAlunoId(a.getId()),
                        planejamentoTreinoRepository.existsCurrentTreinoByAlunoId(a.getId()),
                        planejamentoDietaRepository.findDietaByAlunoId(a.getId()),
                        planejamentoTreinoRepository.findTreinoByAlunoId(a.getId())
                )
        );
    }
}
