package com.example.premarital.quizCategory.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.quizCategory.dto.QuizCategoryDTO;
import com.example.premarital.quizCategory.model.QuizCategory;
import com.example.premarital.quizCategory.repository.QuizCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizCategoryServiceImpl implements QuizCategoryService {
    private final QuizCategoryRepository quizCategoryRepository;

    public QuizCategoryServiceImpl(QuizCategoryRepository quizCategoryRepository) {
        this.quizCategoryRepository = quizCategoryRepository;
    }

    @Override
    public PagingResult<QuizCategoryDTO> getQuizCategories(PaginationRequest request) {
        return null;
    }

    @Override
    public QuizCategoryDTO createQuizCategory(QuizCategoryDTO dto) {
        return null;
    }

    @Override
    public QuizCategory getQuizCategoryById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuizCategoryById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuizCategory(Long id, QuizCategoryDTO updatedQuizCategoryDTO) {
        return false;
    }
}
