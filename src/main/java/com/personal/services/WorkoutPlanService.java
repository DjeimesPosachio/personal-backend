package com.personal.services;

import com.personal.dtos.request.ExerciseMetricsRequestDto;
import com.personal.dtos.request.TrainingRequestDto;
import com.personal.dtos.request.WorkoutPlanRequestDto;
import com.personal.dtos.response.WorkoutPlanResponseDto;
import com.personal.entities.ExerciseMetrics;
import com.personal.entities.Training;
import com.personal.entities.WorkoutPlan;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.IWorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkoutPlanService {
    @Autowired
    private IWorkoutPlanRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    public void create(WorkoutPlanRequestDto dto) {
        WorkoutPlan workoutPlan = WorkoutPlan.builder()
                .dataInicialPlano(dto.getDataInicialPlano())
                .dataFinalPlano(dto.getDataFinalPlano())
                .user(userService.recuperarPorId(dto.getUserId()))
                .build();
        workoutPlan.setTrainings(buildTrainings(workoutPlan, dto.getTrainings()));
        repository.save(workoutPlan);
    }

    public List<WorkoutPlanResponseDto> findAll() {
        return repository.findAll().stream().map(WorkoutPlanResponseDto::new).toList();
    }

    public WorkoutPlanResponseDto findById(Long id) {
        Optional<WorkoutPlan> Training = repository.findById(id);
        return new WorkoutPlanResponseDto(Training.get());
    }

    public WorkoutPlan recuperarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Planejamento não encontrado"));
    }

    public void update(Long id, WorkoutPlanRequestDto dto) {
        WorkoutPlan workoutPlan = recuperarPorId(id);
        workoutPlan.setDataInicialPlano(dto.getDataInicialPlano());
        workoutPlan.setDataFinalPlano(dto.getDataFinalPlano());
        workoutPlan.setUser(userService.recuperarPorId(dto.getUserId()));
        repository.save(workoutPlan);
    }

    public Long delete(@PathVariable Long id) {

        Optional<WorkoutPlan> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());

        return id;
    }

    private List<Training> buildTrainings(WorkoutPlan workoutPlan, List<TrainingRequestDto> trainings) {
        return trainings.stream().map(training -> {
             Training trainingItem = Training.builder()
                    .workoutPlan(workoutPlan)
                    .description(training.getDescription())
                    .build();
             trainingItem.setExerciseMetrics(buildExerciseMetrics(trainingItem, training.getExerciseMetrics()));
             return trainingItem;
        }).collect(Collectors.toList());
    }

    private List<ExerciseMetrics> buildExerciseMetrics(Training training, List<ExerciseMetricsRequestDto> exerciseMetrics) {
        return exerciseMetrics.stream().map(metric -> {
             ExerciseMetrics exerciseMetricsItem = ExerciseMetrics.builder()
                    .durationRest(metric.getDurationRest())
                    .sets(metric.getSets())
                    .sequence(metric.getSequence())
                    .training(training)
                    .exercise(exerciseService.recuperarPorId(metric.getExerciseId()))
                    .build();
             return exerciseMetricsItem;
        }).collect(Collectors.toList());
    }
}
