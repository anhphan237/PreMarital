package com.example.premarital.services;

import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.models.QuizCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizCategoryService {
    Page<QuizCategoryDTO> getQuizCategories(Pageable pageable);
    void createQuizCategory(QuizCategoryDTO dto);
    QuizCategoryDTO getQuizCategoryById(Long id);
    boolean deleteQuizCategoryById(Long id);
    boolean updateQuizCategory(Long id, QuizCategoryDTO updatedQuizCategoryDTO);
}
