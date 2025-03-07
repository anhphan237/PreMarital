package com.example.premarital.mappers;

import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.models.QuizCategory;

public interface QuizCategoryMapper {
    QuizCategoryDTO toDTO(QuizCategory quizCategory);
    QuizCategory toEntity(QuizCategoryDTO dto);
    QuizCategory toEntityWithId(Long id, QuizCategoryDTO dto);
}
