package com.personal.controllers;

import com.personal.dtos.request.AlunoDto;
import com.personal.dtos.response.AlunoResponseDto;
import com.personal.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public Page<AlunoResponseDto> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        return alunoService.findAll(PageRequest.of(page, size));
    }

}
