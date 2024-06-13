package com.personal.repositories;

import com.personal.entities.UsuarioTokenNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioTokenNotificacaoRepository extends JpaRepository<UsuarioTokenNotificacao, Long> {

    Boolean existsByUsuarioIdAndToken(Long usuarioId, String token);

    List<UsuarioTokenNotificacao> findByUsuarioId(Long usuarioId);
}
