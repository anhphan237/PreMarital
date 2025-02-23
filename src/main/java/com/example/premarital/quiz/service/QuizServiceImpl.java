package com.example.premarital.quiz.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.quiz.dto.QuizDTO;
import com.example.premarital.quiz.model.Quiz;
import com.example.premarital.quiz.repository.QuizRepository;
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
