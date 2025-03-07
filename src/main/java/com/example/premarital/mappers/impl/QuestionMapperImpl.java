package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.mappers.QuestionMapper;
import com.example.premarital.models.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapperImpl implements QuestionMapper {
    @Override
    public QuestionDTO toDTO(Question question) {
        if (question == null) {
            return null;
        }
        return new QuestionDTO(
                question.getId(),
                question.getQuestionText(),
                question.getCreatedAt(),
                question.getUpdatedAt(),
                question.getForGender(),
                question.getIsActive()
        );
    }

    @Override
    public Question toEntity(QuestionDTO questionDTO) {
        if (questionDTO == null) {
            return null;
        }
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setCreatedAt(questionDTO.getCreatedAt());
        question.setUpdatedAt(questionDTO.getUpdatedAt());
        question.setForGender(questionDTO.getForGender());
        question.setIsActive(questionDTO.getIsActive());

        return question;
    }

    @Override
    public Question toEntityWithId(Long id, QuestionDTO questionDTO) {
        if (questionDTO == null) {
            return null;
        }
        Question question = new Question();
        question.setId(id);
        question.setQuestionText(questionDTO.getQuestionText());
        question.setCreatedAt(questionDTO.getCreatedAt());
        question.setUpdatedAt(questionDTO.getUpdatedAt());
        question.setForGender(questionDTO.getForGender());
        question.setIsActive(questionDTO.getIsActive());

        return question;
    }
}
