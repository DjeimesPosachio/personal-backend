package com.personal.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.personal.entities.Email;
import com.personal.enums.EStatusEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponseDto {
    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private EStatusEmail statusEmail;

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
