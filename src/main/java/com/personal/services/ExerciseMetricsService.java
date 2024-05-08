package com.personal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.personal.dtos.request.ExerciseMetricsRequestDto;
import com.personal.dtos.response.ExerciseMetricsResponseDto;
import com.personal.entities.Exercise;
import com.personal.entities.ExerciseMetrics;
import com.personal.entities.Training;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.IExerciseMetricsRepository;
import com.personal.repositories.IExerciseRepository;
import com.personal.repositories.ITrainingRepository;

@Service
public class ExerciseMetricsService {
    @Autowired
    private IExerciseMetricsRepository repository;

    @Autowired
    private IExerciseRepository exerciseRepository;

    @Autowired
    private ITrainingRepository trainingRepository;

    public ExerciseMetricsResponseDto create(ExerciseMetricsRequestDto dto) {
        Optional<Training> TrainingOptional = trainingRepository.findById(dto.getTrainingId());
        Optional<Exercise> ExerciseOptional = exerciseRepository.findById(dto.getExerciseId());
        if (!TrainingOptional.isPresent() || !ExerciseOptional.isPresent()) {
            throw new EventNotFoundException("Nao Encontrado");
        }
        ExerciseMetrics metrics = new ExerciseMetrics(dto);
        metrics.setExercise(ExerciseOptional.get());
        metrics.setTraining(TrainingOptional.get());
        return new ExerciseMetricsResponseDto(repository.save(metrics));
    }

    public List<ExerciseMetricsResponseDto> findAll() {
        return repository.findAll().stream().map(ExerciseMetricsResponseDto::new).toList();
    }

    public ExerciseMetricsResponseDto findById(@PathVariable Long id) {
        Optional<ExerciseMetrics> metrics = repository.findById(id);
        return new ExerciseMetricsResponseDto(metrics.get());
    }

    public Long delete(@PathVariable Long id) {
        Optional<ExerciseMetrics> opexercise = repository.findById(id);
        if (!opexercise.isPresent()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());
        return id;
    }
}
