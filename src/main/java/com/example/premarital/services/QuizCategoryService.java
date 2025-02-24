package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.models.QuizCategory;

public interface QuizCategoryService {
    PagingResult<QuizCategoryDTO> getQuizCategories(PaginationRequest request);
    QuizCategoryDTO createQuizCategory(QuizCategoryDTO dto);
    QuizCategory getQuizCategoryById(Long id);
    boolean deleteQuizCategoryById(Long id);
    boolean updateQuizCategory(Long id, QuizCategoryDTO updatedQuizCategoryDTO);
}
