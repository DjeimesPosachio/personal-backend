package com.personal.app.controllers;

import com.personal.app.services.NotificacoesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notificações", description = "Notificações")
@RestController
@RequestMapping("/v1/app/notificacoes")
@RequiredArgsConstructor
public class NotificacoesController {

    private final NotificacoesService notificacoesService;

    @PutMapping("/token")
    public void atualizarToken(@RequestParam String token) {
        notificacoesService.atualizaToken(token);
    }
}
