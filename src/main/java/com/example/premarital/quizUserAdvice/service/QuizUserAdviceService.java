package com.example.premarital.quizUserAdvice.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.quizUserAdvice.dto.QuizUserAdviceDTO;
import com.example.premarital.quizUserAdvice.model.QuizUserAdvice;
import com.example.premarital.quiz.dto.QuizDTO;

public interface QuizUserAdviceService {
    PagingResult<QuizUserAdviceDTO> getQuizUserAdvices(PaginationRequest request);
    QuizUserAdviceDTO createQuizUserAdvice(QuizUserAdviceDTO dto);
    QuizUserAdvice getQuizUserAdviceById(Long id);
    boolean deleteQuizUserAdviceById(Long id);
    boolean updateQuizUserAdvice(Long id, QuizDTO updatedQuizUserAdviceDTO);
}
