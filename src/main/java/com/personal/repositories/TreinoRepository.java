package com.personal.repositories;

import com.personal.entities.TreinoEntitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoRepository extends JpaRepository<TreinoEntitie, Long> {
}
