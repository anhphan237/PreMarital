package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuizHistoryDTO {
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @Min(value = 0, message = "Quiz points cannot be negative")
    private int quizPoint;

    private Long quizUserAdviceId;

    @JsonProperty("isActive")
    private Boolean isActive = true;
}
