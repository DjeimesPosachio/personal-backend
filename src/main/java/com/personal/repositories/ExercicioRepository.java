package com.personal.repositories;

import com.personal.entities.ExercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository extends JpaRepository<ExercicioEntity, Long> {

}