package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.repositories.QuestionOptionRepository;
import com.example.premarital.services.QuestionOptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private final QuestionOptionRepository questionOptionRepository;

    public QuestionOptionServiceImpl(QuestionOptionRepository questionOptionRepository) {
        this.questionOptionRepository = questionOptionRepository;
    }

    @Override
    public Page<QuestionOptionDTO> getQuestionOptions(Pageable pageable) {

        return null;
    }

    @Override
    public void createQuestionOption(QuestionOptionDTO dto) {

    }

    @Override
    public QuestionOption getQuestionOptionById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuestionOptionById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuestionOption(Long id, QuestionOptionDTO updatedQuestionOptionDTO) {
        return false;
    }
}
