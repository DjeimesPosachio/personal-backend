package com.personal.app.services;

import com.personal.entities.UsuarioTokenNotificacao;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.UsuarioTokenNotificacaoRepository;
import com.personal.security.UsuarioLogado;
import com.personal.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacoesService {

    private final UsuarioTokenNotificacaoRepository usuarioTokenNotificacaoRepository;

    public void atualizaToken(String token){

        if(StringUtils.isBlank(token)){
            throw new EventNotFoundException("Token não informado.");
        }

        Boolean existeToken = usuarioTokenNotificacaoRepository.existsByUsuarioIdAndToken(UsuarioLogado.getIdUsuarioLogado(), token);

        if(!existeToken){
            UsuarioTokenNotificacao usuarioToken = UsuarioTokenNotificacao.builder()
                    .usuario(UsuarioLogado.getUsuarioLogado())
                    .token(token)
                    .build();

            usuarioTokenNotificacaoRepository.save(usuarioToken);
        }

    }
}
