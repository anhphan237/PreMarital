package com.example.premarital.Question.service;

import com.example.premarital.Question.dto.QuestionDTO;
import com.example.premarital.Question.model.Question;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface QuestionService {
    PagingResult<QuestionDTO> getQuestions(PaginationRequest request);
    QuestionDTO createQuestion(QuestionDTO dto);
    Question getQuestionById(Long id);
    boolean deleteQuestionById(Long id);
    boolean updateQuestion(Long id, QuestionDTO updatedQuestionDTO);
}
