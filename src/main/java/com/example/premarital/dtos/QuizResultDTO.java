package com.example.premarital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultDTO {
    private Long id;
    private Long quizId;
    private String quizTitle;
    private Integer score;
    private String advice;
    private LocalDateTime completedAt;
    private List<AnswerDetailDTO> answers;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerDetailDTO {
        private Long questionId;
        private String questionText;
        private Long selectedOptionId;
        private String selectedOptionText;
        private Integer points;
    }
} 