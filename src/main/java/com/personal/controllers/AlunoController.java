package com.personal.controllers;

import com.personal.dtos.request.AlunoDto;
import com.personal.entities.AlunoEntity;
import com.personal.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoEntity> cadastrarAluno(@RequestBody AlunoDto alunoDto) {
        AlunoEntity aluno = alunoService.cadastrarAluno(alunoDto);
        return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> obterAlunoPorId(@PathVariable Long id) {
        AlunoEntity aluno = alunoService.recuperarPorId(id);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }
}
