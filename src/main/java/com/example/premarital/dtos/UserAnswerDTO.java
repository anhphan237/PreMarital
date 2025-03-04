package com.example.premarital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDTO {
    private Long id;
    private Long userQuizHistoryId;
    private Long quizQuestionId;
}
