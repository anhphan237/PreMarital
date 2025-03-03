package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.models.QuizCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizCategoryService {
    Page<QuizCategoryDTO> getQuizCategories(Pageable pageable);
    void createQuizCategory(QuizCategoryDTO dto);
    QuizCategory getQuizCategoryById(Long id);
    boolean deleteQuizCategoryById(Long id);
    boolean updateQuizCategory(Long id, QuizCategoryDTO updatedQuizCategoryDTO);
}
