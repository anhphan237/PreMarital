package com.example.premarital.mappers;

import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.models.QuizQuestion;

public interface QuizQuestionMapper {
    QuizQuestionDTO toDTO(QuizQuestion quizQuestion);

    QuizQuestion toEntity(QuizQuestionDTO quizQuestionDTO);
}
