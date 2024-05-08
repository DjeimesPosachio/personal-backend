package com.personal.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.personal.dtos.request.ExerciseRequestDto;
import com.personal.dtos.response.ExerciseResponseDto;
import com.personal.entities.Exercise;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.IExerciseRepository;

@Service
public class ExerciseService {
    @Autowired
    private IExerciseRepository repository;
    @Autowired
    private FileStorageService fileStorageService;

    public ExerciseResponseDto create(
            ExerciseRequestDto dto,
            @RequestPart MultipartFile file) {

        String url = fileStorageService.SaveGif(file);
        Exercise novo = new Exercise(dto);
        novo.setUrlGif(url);
        repository.save(novo);
        return new ExerciseResponseDto(novo);
    }

    public List<ExerciseResponseDto> findAll() {
        return repository.findAll().stream().map(ExerciseResponseDto::new).toList();
    }

    @Async
    public CompletableFuture<List<ExerciseResponseDto>> findAllAsync() throws InterruptedException {

        List<ExerciseResponseDto> lista = repository.findAll().stream().map(ExerciseResponseDto::new).toList();
        return CompletableFuture.completedFuture(lista);
    }

    public ExerciseResponseDto findById(@PathVariable Long id) {
        Optional<Exercise> exercise = repository.findById(id);
        if (exercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        return new ExerciseResponseDto(exercise.get());
    }

    public ExerciseResponseDto update(Long id, ExerciseRequestDto dto) {
        Optional<Exercise> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        Exercise updatedExercise = opexercise.get();
        updatedExercise.setName(dto.getName());
        updatedExercise.setDescription(dto.getDescription());
        updatedExercise.setSets(dto.getSets());
        updatedExercise.setSequence(dto.getSequence());
        return new ExerciseResponseDto(repository.save(updatedExercise));
    }

    public Long delete(@PathVariable Long id) {
        Optional<Exercise> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());
        return id;
    }

}
