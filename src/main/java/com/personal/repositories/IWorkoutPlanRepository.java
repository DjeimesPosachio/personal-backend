package com.personal.repositories;

import com.personal.entities.WorkoutPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
}
