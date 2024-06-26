package com.personal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.entities.MetricasExercicioEntity;

@Repository
public interface MetricasExercicioRepository extends JpaRepository<MetricasExercicioEntity, Long> {

}
