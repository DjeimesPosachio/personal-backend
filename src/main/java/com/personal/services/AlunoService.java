package com.personal.services;

import com.personal.dtos.request.AlunoDto;
import com.personal.dtos.response.AlunoResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.User;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private UserService userService;


    public AlunoEntity create(AlunoDto alunoDto) {
        User user = userService.findById(alunoDto.getUsuarioId());

        AlunoEntity aluno = new AlunoEntity();
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setNome(alunoDto.getNome());
        aluno.setUser(user);

        return alunoRepository.save(aluno);
    }

    public AlunoEntity findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Aluno não encontrado"));
    }

    public void update(AlunoDto alunoDto) {
        AlunoEntity aluno = findById(alunoDto.getId());
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        alunoRepository.save(aluno);
    }


    public AlunoResponseDto recuperarAlunoPeloId(Long id) {
        AlunoEntity aluno = findById(id);

        return new AlunoResponseDto(aluno);
    }


    public Page<AlunoResponseDto> findAll(Pageable pageable) {
        Page<AlunoEntity> alunos = alunoRepository.findAll(pageable);
        return alunos.map(AlunoResponseDto::new);
    }

}
