package com.personal.repositories;

import com.personal.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExerciseRepository extends JpaRepository<Exercise, String> {

}
