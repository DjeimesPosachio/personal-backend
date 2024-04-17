package com.personal.dtos.request;

public record EmailRequestDto(String ownerRef, String emailFrom, String emailTo, String subject, String text) {
}