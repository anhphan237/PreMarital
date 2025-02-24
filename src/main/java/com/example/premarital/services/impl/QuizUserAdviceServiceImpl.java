package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.repositories.QuizUserAdviceRepository;
import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.services.QuizUserAdviceService;
import org.springframework.stereotype.Service;

@Service
public class QuizUserAdviceServiceImpl implements QuizUserAdviceService {
    private final QuizUserAdviceRepository quizUserAdviceRepository;

    public QuizUserAdviceServiceImpl(QuizUserAdviceRepository quizUserAdviceRepository) {
        this.quizUserAdviceRepository = quizUserAdviceRepository;
    }

    @Override
    public PagingResult<QuizUserAdviceDTO> getQuizUserAdvices(PaginationRequest request) {
        return null;
    }

    @Override
    public QuizUserAdviceDTO createQuizUserAdvice(QuizUserAdviceDTO dto) {
        return null;
    }

    @Override
    public QuizUserAdvice getQuizUserAdviceById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuizUserAdviceById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuizUserAdvice(Long id, QuizDTO updatedQuizUserAdviceDTO) {
        return false;
    }
}
