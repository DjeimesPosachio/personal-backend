package com.personal.repositories;

import com.personal.entities.ExercicioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository extends JpaRepository<ExercicioEntity, Long> {

    @Query("SELECT e FROM exercicio e " +
            "WHERE (:nomeExercicio IS NULL OR :nomeExercicio = '' OR UPPER(e.nomeExercicio) LIKE UPPER(CONCAT('%', :nomeExercicio, '%'))) " +
            "ORDER BY e.nomeExercicio")
    Page<ExercicioEntity> findByFilters(@Param("nomeExercicio") String nome, Pageable pageable);


}