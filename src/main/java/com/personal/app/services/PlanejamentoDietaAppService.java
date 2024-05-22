package com.personal.app.services;

import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.PlanejamentoDietaEntity;
import com.personal.entities.PlanejamentoTreinoEntity;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.PlanejamentoDietaRepository;
import com.personal.repositories.PlanejamentoTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlanejamentoDietaAppService {

    @Autowired
    private PlanejamentoDietaRepository planejamentoDietaRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    public PlanejamentoDietaResponseDto recuperarPlanejamentoDietaUsuarioLogado(){

        // TODO ARRANJAR JEITO DE OBTER O USUARIO LOGADO
        Optional<AlunoEntity> aluno = alunoRepository.findByUserId(2L);

        if(aluno.isEmpty())
            throw new RuntimeException("Erro ao recuperar planejamento da dieta. Aluno não encontrado");


        List<PlanejamentoDietaEntity> dieta = planejamentoDietaRepository.findLastByDataAtualAndAlunoId(aluno.get().getId());

        if(dieta.isEmpty())
            throw new RuntimeException("Sem dieta para o aluno.");

        return new PlanejamentoDietaResponseDto(dieta.get(0));



    }
}
