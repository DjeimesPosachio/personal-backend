package com.personal.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.personal.dtos.response.EmailResponseDto;
import com.personal.entities.Email;
import com.personal.enums.EStatusEmail;
import com.personal.repositories.IEmailRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailService {
    @Autowired
    IEmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @SuppressWarnings("finally")
    @Transactional
    public Email sendEmail(Email emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(EStatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(EStatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public List<EmailResponseDto> findAll() {
        return emailRepository.findAll().stream().map(EmailResponseDto::new).toList();
    }

    public EmailResponseDto findById(Long emailId) {
        Optional<Email> temp = emailRepository.findById(emailId);
        Email email = temp.get();
        return new EmailResponseDto(email);
    }

}
