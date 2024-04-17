package com.personal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.entities.Email;

public interface IEmailRepository extends JpaRepository<Email, UUID> {

}
