package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.mappers.QuestionOptionMapper;
import com.example.premarital.models.Question;
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
                entity.getPoint()
        );
    }

    @Override
    public QuestionOption toEntity(QuestionOptionDTO dto) {
        if (dto == null) return null;
        Question question = questionRepository.findById(dto.getQuestionId()).orElse(null);
        return new QuestionOption(
            dto.getId(),
            question,
            dto.getOptionText(),
            dto.getPoint()
        );
    }
}
