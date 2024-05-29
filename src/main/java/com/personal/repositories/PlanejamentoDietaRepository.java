package com.personal.repositories;

import com.personal.entities.PlanejamentoDietaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanejamentoDietaRepository extends JpaRepository<PlanejamentoDietaEntity, Long> {

    @Query("SELECT p FROM planejamentoDieta p " +
            "WHERE CURRENT_DATE BETWEEN p.dataInicialDieta AND p.dataFinalDieta " +
            "AND p.aluno.id = :idAluno " +
            "ORDER BY p.dataInicialDieta DESC")
    List<PlanejamentoDietaEntity> findLastByDataAtualAndAlunoId(Long idAluno);

}