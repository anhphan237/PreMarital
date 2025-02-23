package com.example.premarital.Question.service;

import com.example.premarital.Question.dto.QuestionDTO;
import com.example.premarital.Question.model.Question;
import com.example.premarital.Question.repository.QuestionRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public PagingResult<QuestionDTO> getQuestions(PaginationRequest request) {
        return null;
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO dto) {
        return null;
    }

    @Override
    public Question getQuestionById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuestionById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuestion(Long id, QuestionDTO updatedQuestionDTO) {
        return false;
    }
}
