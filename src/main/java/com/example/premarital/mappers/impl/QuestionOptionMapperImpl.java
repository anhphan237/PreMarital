package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.mappers.QuestionOptionMapper;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.repositories.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class QuestionOptionMapperImpl implements QuestionOptionMapper {
    private final QuestionRepository questionRepository;

    public QuestionOptionMapperImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionOptionDTO toDTO(QuestionOption entity) {
        if (entity == null) return null;
        return new QuestionOptionDTO(
                entity.getId(),
                entity.getQuestion().getId(), // Chỉ lấy ID của câu hỏi
                entity.getOptionText(),
                entity.getPoint(),
                entity.getIsActive()
        );
    }

    @Override
    public QuestionOption toEntity(QuestionOptionDTO dto) {
        if (dto == null) return null;

        QuestionOption questionOption = new QuestionOption();
        questionOption.setId(dto.getId());
        questionOption.setOptionText(dto.getOptionText());
        questionOption.setPoint(dto.getPoint());
        questionOption.setIsActive(dto.getIsActive());
        questionOption.setQuestion(questionRepository.getOne(dto.getQuestionId()));

        return questionOption;
    }

    @Override
    public QuestionOption toEntityWithId(Long id, QuestionOptionDTO dto) {
        if (dto == null) return null;

        QuestionOption questionOption = new QuestionOption();
        questionOption.setId(id);
        questionOption.setOptionText(dto.getOptionText());
        questionOption.setPoint(dto.getPoint());
        questionOption.setIsActive(dto.getIsActive());
        questionOption.setQuestion(questionRepository.getOne(dto.getQuestionId()));

        return questionOption;
    }
}
