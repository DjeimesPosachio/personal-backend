package com.personal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.entities.ExerciseMetrics;

@Repository
public interface IExerciseMetricsRepository extends JpaRepository<ExerciseMetrics, Long> {

}
