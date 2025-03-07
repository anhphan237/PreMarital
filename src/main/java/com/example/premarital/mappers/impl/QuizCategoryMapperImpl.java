package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.mappers.QuizCategoryMapper;
import com.example.premarital.models.Category;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.QuizCategory;
import org.springframework.stereotype.Component;

@Component
public class QuizCategoryMapperImpl implements QuizCategoryMapper {
    @Override
    public QuizCategoryDTO toDTO(QuizCategory quizCategory) {
        if (quizCategory == null) {
            return null;
        }
        return new QuizCategoryDTO(
                quizCategory.getId(),
                quizCategory.getCategory() != null ? quizCategory.getCategory().getId() : null,
                quizCategory.getQuiz() != null ? quizCategory.getQuiz().getId() : null,
                quizCategory.getIsActive()
        );
    }

    @Override
    public QuizCategory toEntity(QuizCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setId(dto.getId());
        quizCategory.setIsActive(dto.getIsActive());

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            quizCategory.setCategory(category);
        }

        if (dto.getQuizId() != null) {
            Quiz quiz = new Quiz();
            quiz.setId(dto.getQuizId());
            quizCategory.setQuiz(quiz);
        }

        return quizCategory;
    }

    @Override
    public QuizCategory toEntityWithId(Long id, QuizCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setId(id);
        quizCategory.setIsActive(dto.getIsActive());

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            quizCategory.setCategory(category);
        }

        if (dto.getQuizId() != null) {
            Quiz quiz = new Quiz();
            quiz.setId(dto.getQuizId());
            quizCategory.setQuiz(quiz);
        }

        return quizCategory;
    }
}
