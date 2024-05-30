package com.personal.app.services;

import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.AlunoRepository;
import com.personal.security.UsuarioLogado;
import com.personal.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;


    public PlanejamentoDietaResponseDto findByLoggedUser(){



        return null;
    }

    public PlanejamentoDietaResponseDto update(){

        AlunoEntity aluno = alunoRepository.findByUserId(UsuarioLogado.getIdUsuarioLogado()).orElseThrow(
                () -> new EventNotFoundException("Aluno não encontrado para o usuário logado."));

        return null;
    }
}
