package com.personal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
}
