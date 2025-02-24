package com.example.premarital.services;

import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface QuizQuestionService {
    PagingResult<QuizQuestionDTO> getQuizQuestions(PaginationRequest request);
    QuizQuestionDTO createQuizQuestion(QuizQuestionDTO dto);
    QuizQuestion getQuizQuestionById(Long id);
    boolean deleteQuizQuestionById(Long id);
    boolean updateQuizQuestion(Long id, QuizQuestionDTO updatedQuizQuestionDTO);
}
