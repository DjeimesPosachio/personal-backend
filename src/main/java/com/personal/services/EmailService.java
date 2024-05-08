package com.personal.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.personal.dtos.response.EmailResponseDto;
import com.personal.entities.Email;
import com.personal.enums.EStatusEmail;
import com.personal.repositories.IEmailRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    @Autowired
    IEmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @SuppressWarnings("finally")
    @Transactional
    public Email sendEmail(Email emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true);

            Context ctx = new Context();
            String htmlcontent = this.htmlTemplateEngine.process("registration", ctx);
            email.setTo(emailModel.getEmailTo());
            email.setSubject(emailModel.getSubject());
            email.setFrom(emailModel.getEmailFrom());
            email.setText(htmlcontent, true);
            ClassPathResource clr = new ClassPathResource("templates/Logo.png");
            email.addInline("Logo", clr, "image/png");
            emailSender.send(mimeMessage);
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

    public EmailResponseDto findById(UUID emailId) {
        Optional<Email> temp = emailRepository.findById(emailId);
        Email email = temp.get();
        return new EmailResponseDto(email);
    }

}
