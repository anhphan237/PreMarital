package com.example.premarital.quizQuestion.service;

import com.example.premarital.quizQuestion.dto.QuizQuestionDTO;
import com.example.premarital.quizQuestion.model.QuizQuestion;
import com.example.premarital.quizQuestion.repository.QuizQuestionRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
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
