package com.personal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.personal.dtos.request.MetricasExerciciosRequestDto;
import com.personal.dtos.response.MetricasExercicioResponseDto;
import com.personal.entities.ExercicioEntity;
import com.personal.entities.MetricasExercicioEntity;
import com.personal.entities.TreinoEntity;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.MetricasExercicioRepository;
import com.personal.repositories.ExercicioRepository;
import com.personal.repositories.TreinoRepository;

@Service
public class MetricasExercicioService {
    @Autowired
    private MetricasExercicioRepository repository;

    @Autowired
    private ExercicioRepository exerciseRepository;

    @Autowired
    private TreinoRepository trainingRepository;

    public MetricasExercicioResponseDto create(MetricasExerciciosRequestDto dto) {
        Optional<TreinoEntity> TrainingOptional = trainingRepository.findById(dto.getTreinoId());
        Optional<ExercicioEntity> ExerciseOptional = exerciseRepository.findById(dto.getExercicioId());
        if (!TrainingOptional.isPresent() || !ExerciseOptional.isPresent()) {
            throw new EventNotFoundException("Nao Encontrado");
        }
        MetricasExercicioEntity metrics = new MetricasExercicioEntity(dto);
        metrics.setExercicioEntity(ExerciseOptional.get());
        metrics.setTreinoEntity(TrainingOptional.get());
        return new MetricasExercicioResponseDto(repository.save(metrics));
    }

    public List<MetricasExercicioResponseDto> findAll() {
        return repository.findAll().stream().map(MetricasExercicioResponseDto::new).toList();
    }

    public MetricasExercicioResponseDto findById(@PathVariable Long id) {
        Optional<MetricasExercicioEntity> metrics = repository.findById(id);
        return new MetricasExercicioResponseDto(metrics.get());
    }

    public Long delete(@PathVariable Long id) {
        Optional<MetricasExercicioEntity> opexercise = repository.findById(id);
        if (!opexercise.isPresent()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());
        return id;
    }
}
