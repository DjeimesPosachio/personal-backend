package com.personal.repositories;

import com.personal.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByEmail(String email);
}
