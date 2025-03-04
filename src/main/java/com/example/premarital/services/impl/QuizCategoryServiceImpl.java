package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.QuizCategoryMapper;
import com.example.premarital.models.QuizCategory;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.QuizCategoryRepository;
import com.example.premarital.services.QuizCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuizCategoryServiceImpl implements QuizCategoryService {
    private final QuizCategoryRepository quizCategoryRepository;
    private final QuizCategoryMapper quizCategoryMapper;

    public QuizCategoryServiceImpl(QuizCategoryRepository quizCategoryRepository, QuizCategoryMapper quizCategoryMapper) {
        this.quizCategoryRepository = quizCategoryRepository;
        this.quizCategoryMapper = quizCategoryMapper;
    }

    @Override
    public Page<QuizCategoryDTO> getQuizCategories(Pageable pageable) {
        Page<QuizCategory> entities = quizCategoryRepository.findAll(pageable);
        Page<QuizCategoryDTO> dtoPage = entities.map(new Function<QuizCategory, QuizCategoryDTO>() {

            @Override
            public QuizCategoryDTO apply(QuizCategory quizCategory) {
                QuizCategoryDTO dto = quizCategoryMapper.toDTO(quizCategory);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createQuizCategory(QuizCategoryDTO dto) {
        QuizCategory quizCategory = quizCategoryMapper.toEntity(dto);
        quizCategoryRepository.save(quizCategory);
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
