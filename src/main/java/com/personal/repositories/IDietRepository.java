package com.personal.repositories;

import com.personal.entities.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDietRepository extends JpaRepository<Diet, String> {
}
