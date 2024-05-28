package com.personal.security;

import com.personal.entities.User;
import com.personal.exceptions.EventNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UsuarioLogado {
    public static Long getIdUsuarioLogado() {
        User usuario = getUsuarioLogado();
        return usuario.getId();
    }

    public static User getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> principal = (Optional<User>) authentication.getPrincipal();

        if (principal.isEmpty())
            throw new EventNotFoundException("Usuário logado não encontrado ou não autenticado");

        return principal.get();
    }
}
