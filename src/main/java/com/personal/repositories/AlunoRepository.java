package com.personal.repositories;

import com.personal.entities.AlunoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    Optional<AlunoEntity> findByUserId(Long usuarioId);
    @Query("SELECT a FROM aluno a " +
            "WHERE (:nomeAluno IS NULL OR :nomeAluno = '' OR UPPER(a.nome) LIKE UPPER(CONCAT('%', :nomeAluno, '%'))) " +
            "ORDER BY a.nome")
    Page<AlunoEntity> findByFilters(@Param("nomeAluno") String nome, Pageable pageable);


}
