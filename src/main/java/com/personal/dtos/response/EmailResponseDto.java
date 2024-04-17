package com.personal.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.personal.entities.Email;
import com.personal.enums.EStatusEmail;

public record EmailResponseDto(
        UUID id,
        String ownerRef,
        String emailFrom,
        String emailTo,
        String subject,
        String text,
        LocalDateTime sendDateEmail,
        EStatusEmail statusEmail) {

    public EmailResponseDto(Email email) {
        this(email.getId(),
                email.getOwnerRef(),
                email.getEmailFrom(),
                email.getEmailTo(),
                email.getSubject(),
                email.getText(),
                email.getSendDateEmail(),
                email.getStatusEmail());
    }
}
