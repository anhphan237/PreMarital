package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.repositories.QuizQuestionRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.services.QuizQuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuizQuestionServiceImpl implements QuizQuestionService {
    private final QuizQuestionRepository quizQuestionRepository;

    public QuizQuestionServiceImpl(QuizQuestionRepository quizQuestionRepository) {
        this.quizQuestionRepository = quizQuestionRepository;
    }

    @Override
    public PagingResult<QuizQuestionDTO> getQuizQuestions(PaginationRequest request) {
        return null;
    }

    @Override
    public QuizQuestionDTO createQuizQuestion(QuizQuestionDTO dto) {
        return null;
    }

    @Override
    public QuizQuestion getQuizQuestionById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuizQuestionById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuizQuestion(Long id, QuizQuestionDTO updatedQuizQuestionDTO) {
        return false;
    }
}
