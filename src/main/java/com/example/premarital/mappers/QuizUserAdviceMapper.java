package com.example.premarital.mappers;

import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.models.QuizUserAdvice;

public interface QuizUserAdviceMapper {
    QuizUserAdviceDTO toDTO(QuizUserAdvice quizUserAdvice);
    QuizUserAdvice toEntity(QuizUserAdviceDTO quizUserAdviceDTO);
}
