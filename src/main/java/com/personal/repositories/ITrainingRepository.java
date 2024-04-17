package com.personal.repositories;

import com.personal.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITrainingRepository extends JpaRepository<Training, Long> {
}
