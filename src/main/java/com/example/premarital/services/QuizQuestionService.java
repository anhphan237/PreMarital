package com.example.premarital.services;

import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizQuestionService {
    Page<QuizQuestionDTO> getQuizQuestions(Pageable pageable);
    void createQuizQuestion(QuizQuestionDTO dto);
    QuizQuestion getQuizQuestionById(Long id);
    boolean deleteQuizQuestionById(Long id);
    boolean updateQuizQuestion(Long id, QuizQuestionDTO updatedQuizQuestionDTO);
}
