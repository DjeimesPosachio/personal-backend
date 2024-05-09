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

    public List<TrainingResponseDto> findAll() {
        return repository.findAll().stream().map(TrainingResponseDto::new).toList();
    }
}
