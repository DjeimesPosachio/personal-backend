package com.personal.services;

import com.personal.dtos.response.TrainingResponseDto;
import com.personal.repositories.ITrainingRepository;
import com.personal.repositories.IWorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private ITrainingRepository repository;
    @Autowired
    private IWorkoutPlanRepository workoutPlanrepository;

    public List<TrainingResponseDto> findAll() {
        return repository.findAll().stream().map(TrainingResponseDto::new).toList();
    }
}
