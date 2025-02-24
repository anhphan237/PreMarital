package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.dtos.QuizDTO;

public interface QuizUserAdviceService {
    PagingResult<QuizUserAdviceDTO> getQuizUserAdvices(PaginationRequest request);
    QuizUserAdviceDTO createQuizUserAdvice(QuizUserAdviceDTO dto);
    QuizUserAdvice getQuizUserAdviceById(Long id);
    boolean deleteQuizUserAdviceById(Long id);
    boolean updateQuizUserAdvice(Long id, QuizDTO updatedQuizUserAdviceDTO);
}
