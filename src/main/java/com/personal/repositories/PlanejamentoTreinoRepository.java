package com.personal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.entities.PlanejamentoTreinoEntity;

@Repository
public interface PlanejamentoTreinoRepository extends JpaRepository<PlanejamentoTreinoEntity, Long> {

}
