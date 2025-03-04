package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.mappers.QuestionMapper;
import com.example.premarital.models.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapperImpl implements QuestionMapper {
    @Override
    public QuestionDTO toDTO(Question question) {
        if (question == null) return null;
        return new QuestionDTO(
            question.getId(),
            question.getQuestionText(),
            question.getCreatedAt(),
            question.getUpdatedAt(),
            question.getForGender()
        );
    }

    @Override
    public Question toEntity(QuestionDTO questionDTO) {
        if (questionDTO == null) return null;
        return new Question(
            questionDTO.getId(),
            null, // quizQuestion sẽ được xử lý riêng
            questionDTO.getQuestionText(),
            questionDTO.getCreatedAt(),
            questionDTO.getUpdatedAt(),
            questionDTO.getForGender(),
            null
        );
    }
}
