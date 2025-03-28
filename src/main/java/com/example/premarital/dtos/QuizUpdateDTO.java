package com.example.premarital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizUpdateDTO {
    private String title;
    private String description;
    private Boolean isActive;
    private List<Long> existingQuestionIds;
    private List<Long> removeQuestionIds;
    private List<QuestionDTO> newQuestions;
    private List<QuizAdviceDTO> advices;
} 