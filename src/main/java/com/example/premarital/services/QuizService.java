package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.models.Quiz;

public interface QuizService {
    PagingResult<QuizDTO> getQuizzes(PaginationRequest request);
    QuizDTO createQuiz(QuizDTO dto);
    Quiz getQuizById(Long id);
    boolean deleteQuizById(Long id);
    boolean updateQuiz(Long id, QuizDTO updatedQuizDTO);
}
