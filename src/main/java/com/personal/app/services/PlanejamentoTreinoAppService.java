package com.personal.app.services;

import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.PlanejamentoTreinoEntity;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.PlanejamentoTreinoRepository;
import com.personal.security.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlanejamentoTreinoAppService {

    @Autowired
    private PlanejamentoTreinoRepository planejamentoTreinoRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    public PlanejamentoTreinoResponseDto recuperarPlanejamentoTreinoUsuarioLogado(){

        Optional<AlunoEntity> aluno = alunoRepository.findByUserId(UsuarioLogado.getIdUsuarioLogado());

        if(aluno.isEmpty())
            throw new RuntimeException("Erro ao recuperar planejamento de treino. Aluno não encontrado");

        List<PlanejamentoTreinoEntity> planejamento = planejamentoTreinoRepository.findLastByDataAtualAndAlunoId(aluno.get().getId());

        if(planejamento.isEmpty())
            throw new RuntimeException("Sem planejamento de treino para o aluno.");

        return new PlanejamentoTreinoResponseDto(planejamento.get(0));
    }
}
