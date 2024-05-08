package com.personal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.entities.WorkoutPlan;

@Repository
public interface IWorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {

}
