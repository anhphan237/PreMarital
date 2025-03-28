package com.example.premarital.dtos;

import com.example.premarital.models.Quiz;
import com.example.premarital.models.QuizQuestion;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDTO {

    private Long id;

    private String title;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    private List<QuestionDTO> questions;

    private List<QuizAdviceDTO> advices;

    public static QuizDTO of(Quiz quiz) {
        return QuizDTO.builder()
                .id(quiz.getQuizId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .createdAt(quiz.getCreatedAt())
                .updatedAt(quiz.getUpdatedAt())
                .questions(quiz.getQuizQuestions().stream().map(QuizQuestion::getQuestion).map(QuestionDTO::of).toList())
                .advices(quiz.getAdvices().stream().map(QuizAdviceDTO::of).toList())
                .build();
    }
}
