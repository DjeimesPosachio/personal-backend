package com.personal.controllers;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.dtos.request.EmailRequestDto;
import com.personal.dtos.response.EmailResponseDto;
import com.personal.entities.Email;
import com.personal.services.EmailService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Email", description = "Endpoints de exemplo")
@RestController
@RequestMapping("/v1/email")
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping()
    public ResponseEntity<EmailResponseDto> sendingEmail(@RequestBody EmailRequestDto emailDto) {

        Email sendEmail = emailService.sendEmail(new Email(emailDto));
        return new ResponseEntity<>(new EmailResponseDto(sendEmail), HttpStatus.CREATED);
    }

    @GetMapping("/emails")
    public ResponseEntity<List<EmailResponseDto>> getAllEmails() {

        return new ResponseEntity<>(emailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value = "emailId") UUID emailId) {
        EmailResponseDto emailModelOptional = emailService.findById(emailId);
        if (emailModelOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional);
        }
    }
}
