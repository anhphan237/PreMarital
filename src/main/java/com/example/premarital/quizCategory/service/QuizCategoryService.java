package com.example.premarital.quizCategory.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.quizCategory.dto.QuizCategoryDTO;
import com.example.premarital.quizCategory.model.QuizCategory;

public interface QuizCategoryService {
    PagingResult<QuizCategoryDTO> getQuizCategories(PaginationRequest request);
    QuizCategoryDTO createQuizCategory(QuizCategoryDTO dto);
    QuizCategory getQuizCategoryById(Long id);
    boolean deleteQuizCategoryById(Long id);
    boolean updateQuizCategory(Long id, QuizCategoryDTO updatedQuizCategoryDTO);
}
