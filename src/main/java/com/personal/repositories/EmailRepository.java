package com.personal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.entities.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {

}
