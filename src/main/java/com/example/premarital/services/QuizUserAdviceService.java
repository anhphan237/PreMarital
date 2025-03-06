package com.example.premarital.services;

import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.dtos.QuizDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizUserAdviceService {
    Page<QuizUserAdviceDTO> getQuizUserAdvices(Pageable pageable);
    void createQuizUserAdvice(QuizUserAdviceDTO dto);
    QuizUserAdvice getQuizUserAdviceById(Long id);
    boolean deleteQuizUserAdviceById(Long id);
    boolean updateQuizUserAdvice(Long id, QuizDTO updatedQuizUserAdviceDTO);
}
