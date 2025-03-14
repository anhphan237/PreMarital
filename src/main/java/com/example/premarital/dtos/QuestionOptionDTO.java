package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionDTO {
    private Long id;

    @NotNull(message = "Question ID cannot be null")
    private Long questionId;

    @NotBlank(message = "Option text cannot be empty")
    private String optionText;

    @Min(value = 0, message = "Point value must be 0 or greater")
    private int point;

    @JsonProperty("isActive")
    private Boolean isActive;
}
