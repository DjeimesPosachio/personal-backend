package com.personal.services;

import com.personal.dtos.request.ExercicioRequestDto;
import com.personal.dtos.response.ExercicioResponseDto;
import com.personal.entities.ExercicioEntitie;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ExercicioService {
    @Autowired
    private ExercicioRepository repository;
    @Autowired
    private FileStorageService fileStorageService;

    public ExercicioResponseDto create(
            ExercicioRequestDto dto,
            @RequestPart MultipartFile file) {

        String url = fileStorageService.SaveGif(file);
        ExercicioEntitie novo = new ExercicioEntitie(dto);
        novo.setUrlGif(url);
        repository.save(novo);
        return new ExercicioResponseDto(novo);
    }

    public List<ExercicioResponseDto> findAll() {
        return repository.findAll().stream().map(ExercicioResponseDto::new).toList();
    }

    @Async
    public CompletableFuture<List<ExercicioResponseDto>> findAllAsync() throws InterruptedException {

        List<ExercicioResponseDto> lista = repository.findAll().stream().map(ExercicioResponseDto::new).toList();
        return CompletableFuture.completedFuture(lista);
    }

    public ExercicioResponseDto findById(@PathVariable Long id) {
        Optional<ExercicioEntitie> exercise = repository.findById(id);
        if (exercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        return new ExercicioResponseDto(exercise.get());
    }

    public ExercicioEntitie recuperarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Exercício não encontrado"));
    }

    public ExercicioResponseDto update(Long id, ExercicioRequestDto dto) {
        Optional<ExercicioEntitie> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        ExercicioEntitie updatedExercicioEntitie = opexercise.get();
        updatedExercicioEntitie.setFileName(dto.getName());
        updatedExercicioEntitie.setSeries(dto.getSeries());
        updatedExercicioEntitie.setRepeticoes(dto.getRepeticoes());
        return new ExercicioResponseDto(repository.save(updatedExercicioEntitie));
    }

    public Long delete(@PathVariable Long id) {
        Optional<ExercicioEntitie> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());
        return id;
    }

}
