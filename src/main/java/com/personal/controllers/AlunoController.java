package com.personal.controllers;

import com.personal.dtos.request.AlunoDto;
import com.personal.dtos.response.AlunoResponseDto;
import com.personal.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<HttpStatus> cadastrarAluno(@RequestBody AlunoDto alunoDto) {
        alunoService.cadastrarAluno(alunoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> editarAluno(@RequestBody AlunoDto alunoDto) {
        alunoService.editarAluno(alunoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<AlunoResponseDto> obterAlunoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(alunoService.recuperarAlunoPeloId(id), HttpStatus.OK);
    }

    public Page<AlunoResponseDto> buscarAlunos(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        return alunoService.buscarAlunos(PageRequest.of(page, size));
    }

}
