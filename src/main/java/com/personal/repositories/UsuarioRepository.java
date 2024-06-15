package com.personal.repositories;

import com.personal.entities.User;
import com.personal.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

    Optional<UserDetails> findByEmail(String email);

    @Query("SELECT u FROM usuario u " +
            "WHERE (:status IS NULL OR u.status = :status) " +
            "AND (:nome IS NULL OR :nome = '' OR UPPER(u.nome) LIKE UPPER(CONCAT('%', :nome, '%'))) " +
            "ORDER BY u.nome")
    Page<User> findByFilters(@Param("nome") String nome, @Param("status") UserStatus status, Pageable pageable);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

}
