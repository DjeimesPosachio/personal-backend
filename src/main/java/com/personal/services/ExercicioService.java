package com.personal.services;

import com.google.common.collect.Lists;
import com.personal.dtos.request.ExercicioRequestDto;
import com.personal.dtos.response.ExercicioResponseDto;
import com.personal.entities.ExercicioEntity;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.ExercicioRepository;
import com.personal.utils.StringUtils;
import com.personal.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository repository;

    public ExercicioResponseDto create(
            ExercicioRequestDto dto) {

        ValidatorUtils.throwError(validar(dto));
        ExercicioEntity novo = new ExercicioEntity(dto);
        repository.save(novo);
        return new ExercicioResponseDto(novo);
    }

    @Async
    public CompletableFuture<List<ExercicioResponseDto>> findAllAsync() throws InterruptedException {

        List<ExercicioResponseDto> lista = repository.findAll().stream().map(ExercicioResponseDto::new).toList();
        return CompletableFuture.completedFuture(lista);
    }

    public ExercicioEntity findById(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new EventNotFoundException("Exercício não encontrado"));

    }

    public ExercicioResponseDto recuperarPorId(Long id) {
        ExercicioEntity exercicio = findById(id);
        return new ExercicioResponseDto(exercicio);
    }

    public ExercicioResponseDto update(Long id, ExercicioRequestDto dto) {
        ExercicioEntity exercicio = findById(id);

        ValidatorUtils.throwError(validar(dto));

        exercicio.setNomeExercicio(dto.getNomeExercicio());
        return new ExercicioResponseDto(repository.save(exercicio));
    }

    public Page<ExercicioResponseDto> findAll(String nomeExercicio, Pageable pageable) {
        Page<ExercicioEntity> exercicios = repository.findByFilters(nomeExercicio, pageable);

        return exercicios.map(ExercicioResponseDto::new);
    }


    public Long delete(@PathVariable Long id) {
        ExercicioEntity exercicio = findById(id);

        repository.delete(exercicio);
        return id;
    }

    private List<String> validar(ExercicioRequestDto dto){

        List<String> errors = Lists.newArrayList();

        if(StringUtils.isBlank(dto.getNomeExercicio()))
            errors.add("Nome exercício não informado");

        return errors;

    }

}
