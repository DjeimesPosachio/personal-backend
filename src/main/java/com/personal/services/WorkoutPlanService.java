package com.personal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.personal.dtos.request.WorkoutPlanRequestDto;
import com.personal.dtos.response.WorkoutPlanResponseDto;
import com.personal.entities.WorkoutPlan;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.IWorkoutPlanRepository;

@Service
public class WorkoutPlanService {
    @Autowired
    private IWorkoutPlanRepository repository;

    public WorkoutPlanResponseDto create(WorkoutPlanRequestDto dto) {
        return new WorkoutPlanResponseDto(repository.save(new WorkoutPlan(dto)));
    }

    public List<WorkoutPlanResponseDto> findAll() {
        return repository.findAll().stream().map(WorkoutPlanResponseDto::new).toList();
    }

    public WorkoutPlanResponseDto findById(Long id) {
        Optional<WorkoutPlan> Training = repository.findById(id);
        return new WorkoutPlanResponseDto(Training.get());
    }

    public WorkoutPlanResponseDto update(Long id, WorkoutPlanRequestDto newData) {
        Optional<WorkoutPlan> plan = repository.findById(id);
        if (plan.isEmpty()) {
            throw new Error("nao existe");
        }
        WorkoutPlan teste = plan.get();

        WorkoutPlan updatedTraining = repository.save(teste);
        return new WorkoutPlanResponseDto(updatedTraining);
    }

    public Long delete(@PathVariable Long id) {

        Optional<WorkoutPlan> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());

        return id;
    }
}
