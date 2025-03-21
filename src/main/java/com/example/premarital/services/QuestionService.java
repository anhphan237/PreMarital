package com.example.premarital.services;

import com.example.premarital.dtos.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Page<QuestionDTO> getQuestions(Pageable pageable);
    void createQuestion(QuestionDTO dto);
    QuestionDTO getQuestionById(Long id);
    boolean deleteQuestionById(Long id);
    boolean updateQuestion(Long id, QuestionDTO updatedQuestionDTO);
}
