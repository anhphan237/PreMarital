package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestionDTO {
    private Long id; // Nullable (for updates)

    @NotNull(message = "Question ID cannot be null")
    private Long questionId;

    @NotNull(message = "Quiz ID cannot be null")
    private Long quizId;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @JsonProperty("isActive")
    private Boolean isActive = true;
}
