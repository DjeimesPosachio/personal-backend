package com.personal.repositories;

import com.personal.entities.PlanejamentoTreinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanejamentoTreinoRepository extends JpaRepository<PlanejamentoTreinoEntity, Long> {

    @Query("SELECT p FROM planejamentoTreino p " +
            "WHERE CURRENT_DATE BETWEEN p.dataInicialPlano AND p.dataFinalPlano " +
            "AND p.aluno.id = :idAluno " +
            "ORDER BY p.dataInicialPlano DESC")
    List<PlanejamentoTreinoEntity> findLastByDataAtualAndAlunoId(Long idAluno);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM planejamentoTreino p " +
            "WHERE CURRENT_DATE BETWEEN p.dataInicialPlano AND p.dataFinalPlano " +
            "AND p.aluno.id = :idAluno")
    boolean existsCurrentTreinoByAlunoId(@Param("idAluno") Long idAluno);
}
