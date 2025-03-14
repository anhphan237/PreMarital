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
public class QuizUserAdviceDTO {
    private Long id;

    @NotNull(message = "Quiz ID cannot be null")
    private Long quizId;

    @NotBlank(message = "Advice text cannot be empty")
    private String adviceText;

    @Min(value = 0, message = "From point must be at least 0")
    private int fromPoint;

    @Min(value = 0, message = "To point must be at least 0")
    private int toPoint;

    private Long userQuizHistoryId;

    @JsonProperty("isActive")
    private Boolean isActive = true;
}
