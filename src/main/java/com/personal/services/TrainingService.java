package com.personal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.personal.dtos.request.TrainingRequestDto;
import com.personal.dtos.response.TrainingResponseDto;
import com.personal.entities.Training;
import com.personal.entities.WorkoutPlan;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.ITrainingRepository;
import com.personal.repositories.IWorkoutPlanRepository;

@Service
public class TrainingService {
    @Autowired
    private ITrainingRepository repository;
    @Autowired
    private IWorkoutPlanRepository workoutPlanrepository;

    public TrainingResponseDto create(@RequestBody TrainingRequestDto dto) {
        Optional<WorkoutPlan> workoutTrainingOptional = workoutPlanrepository.findById(dto.getWorkoutPlanId());
        if (workoutTrainingOptional.isEmpty()) {
            throw new EventNotFoundException("Nao Encontrado");
        }

        WorkoutPlan workoutTraining = workoutTrainingOptional.get();
        Training training = new Training(dto);
        training.setWorkoutPlan(workoutTraining);
        return new TrainingResponseDto(repository.save(training));

    }

    public List<TrainingResponseDto> findAll() {
        return repository.findAll().stream().map(TrainingResponseDto::new).toList();
    }

    public TrainingResponseDto findById(@PathVariable Long id) {
        Optional<Training> Training = repository.findById(id);
        return new TrainingResponseDto(Training.get());
    }

    public TrainingResponseDto update(@PathVariable Long id,
            TrainingRequestDto newData) {
        Optional<Training> training = repository.findById(id);
        if (training.isEmpty()) {
            throw new Error("nao existe");
        }
        Training teste = training.get();

        Training updatedTraining = repository.save(teste);
        return new TrainingResponseDto(updatedTraining);
    }

    public Long delete(@PathVariable Long id) {
        Optional<Training> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());
        return id;
    }
}
