package com.example.premarital.dtos;

import com.example.premarital.models.Quiz;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizUserAdviceDTO {
    private Long id;
    private Long quizId;
    private String adviceText;
    private int fromPoint;
    private int toPoint;
    private Long userQuizHistoryId;
    @JsonProperty("isActive")
    private Boolean isActive;
}
