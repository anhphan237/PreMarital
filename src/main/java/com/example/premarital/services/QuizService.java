package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.models.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {
    Page<QuizDTO> getQuizzes(Pageable pageable);
    void createQuiz(QuizDTO dto);
    Quiz getQuizById(Long id);
    boolean deleteQuizById(Long id);
    boolean updateQuiz(Long id, QuizDTO updatedQuizDTO);
}
