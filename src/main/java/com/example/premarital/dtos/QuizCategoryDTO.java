package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizCategoryDTO {
    private Long id;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @NotNull(message = "Quiz ID cannot be null")
    private Long quizId;

    @JsonProperty("isActive")
    private Boolean isActive;
}
