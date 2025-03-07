package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizCategoryDTO {
    private Long id;
    private Long categoryId;
    private Long quizId;
    @JsonProperty("isActive")
    private Boolean isActive;
}
