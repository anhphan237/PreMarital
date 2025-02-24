package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.models.Quiz;
import com.example.premarital.services.QuizService;
import com.example.premarital.repositories.QuizRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public PagingResult<QuizDTO> getQuizzes(PaginationRequest request) {
        return null;
    }

    @Override
    public QuizDTO createQuiz(QuizDTO dto) {
        return null;
    }

    @Override
    public Quiz getQuizById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuizById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuiz(Long id, QuizDTO updatedQuizDTO) {
        return false;
    }
}
