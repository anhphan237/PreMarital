package com.example.premarital.mappers;

import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.models.Quiz;

public interface QuizMapper {
    QuizDTO toDTO(Quiz quiz);
    Quiz toEntity(QuizDTO quizDTO);
}
