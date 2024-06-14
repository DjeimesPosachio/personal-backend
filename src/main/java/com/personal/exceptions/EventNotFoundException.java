package com.personal.exceptions;


import java.util.List;

public class EventNotFoundException extends RuntimeException {

    private final List<String> messages;

    public EventNotFoundException() {
        super("Evento não encontrado");
        this.messages = List.of("Evento não encontrado");
    }

    public EventNotFoundException(String message) {
        super(message);
        this.messages = List.of(message);
    }

    public EventNotFoundException(List<String> messages) {
        super(String.join(", ", messages)); // Para garantir que a mensagem da exceção contenha todas as mensagens
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}