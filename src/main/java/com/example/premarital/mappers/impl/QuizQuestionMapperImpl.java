package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.mappers.QuizQuestionMapper;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.repositories.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class QuizQuestionMapperImpl implements QuizQuestionMapper {
    private final QuestionRepository questionRepository;

    public QuizQuestionMapperImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public QuizQuestionDTO toDTO(QuizQuestion quizQuestion) {
        return null;
    }

    @Override
    public QuizQuestion toEntity(QuizQuestionDTO quizQuestionDTO) {
        return null;
    }
}
