package com.example.premarital.services;

import com.example.premarital.dtos.QuizQuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizQuestionService {
    Page<QuizQuestionDTO> getQuizQuestions(Pageable pageable);
    void createQuizQuestion(QuizQuestionDTO dto);
    QuizQuestionDTO getQuizQuestionById(Long id);
    boolean deleteQuizQuestionById(Long id);
    boolean updateQuizQuestion(Long id, QuizQuestionDTO updatedQuizQuestionDTO);
}
