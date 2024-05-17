package com.personal.services;

import com.personal.dtos.request.AlunoDto;
import com.personal.dtos.response.AlunoResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.User;
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



    public AlunoEntity cadastrarAluno(AlunoDto alunoDto) {
        User user = userRepository.findById(alunoDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + alunoDto.getUserId()));

        AlunoEntity aluno = new AlunoEntity();
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setUser(user);

        return alunoRepository.save(aluno);
    }

    public void editarAluno(AlunoDto alunoDto) {

        AlunoEntity aluno = recuperarPorId(alunoDto.getId());

        aluno.setDataNascimento(alunoDto.getDataNascimento());

        alunoRepository.save(aluno);
    }


    public AlunoEntity recuperarPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public AlunoResponseDto recuperarAlunoPeloId(Long id) {

        AlunoEntity aluno = recuperarPorId(id);

        return convertToDto(aluno);
    }


    public Page<AlunoResponseDto> buscarAlunos(Pageable pageable) {

        Page<AlunoEntity> alunos = alunoRepository.findAll(pageable);


        return alunos.map(this::convertToDto);

    }

    // TODO colocar num converter ou algo do tipo
    public AlunoResponseDto convertToDto(AlunoEntity aluno) {
        return AlunoResponseDto.builder()
                .dataNascimento(aluno.getDataNascimento())
                .usuario(userService.convertToDto(aluno.getUser()))
                .build();
    }

}

