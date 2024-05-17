package com.personal.services;

import com.personal.dtos.request.AlunoDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.User;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository userRepository;
    public AlunoEntity cadastrarAluno(AlunoDto alunoDto) {
        User user = userRepository.findById(alunoDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + alunoDto.getUserId()));

        AlunoEntity aluno = new AlunoEntity();
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setUser(user);

        return alunoRepository.save(aluno);
    }

    public AlunoEntity recuperarPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}

