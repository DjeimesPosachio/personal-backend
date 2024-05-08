package com.personal.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRequestDto {
    @NotBlank(message = "description cannot be blank")
    private String name;
    private String description;
    private long sets;
    private long sequence;

}
