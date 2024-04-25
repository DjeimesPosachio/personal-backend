package com.personal.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.personal.dtos.request.EmailRequestDto;
import com.personal.enums.EStatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "email_entity")
@Table(name = "email_entity")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private EStatusEmail statusEmail;

    public Email(EmailRequestDto email) {
        this.ownerRef = email.ownerRef();
        this.emailFrom = email.emailFrom();
        this.emailTo = email.emailTo();
        this.subject = email.subject();
        this.text = email.text();
    }

}