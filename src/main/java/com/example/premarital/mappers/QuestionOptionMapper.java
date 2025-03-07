package com.example.premarital.mappers;

import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.models.QuestionOption;

public interface QuestionOptionMapper {
    QuestionOptionDTO toDTO(QuestionOption questionOption);
    QuestionOption toEntity(QuestionOptionDTO questionOptionDTO);
    QuestionOption toEntityWithId(Long id, QuestionOptionDTO questionOptionDTO);
}
