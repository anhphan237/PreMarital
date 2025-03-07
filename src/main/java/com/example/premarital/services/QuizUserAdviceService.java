package com.example.premarital.services;

import com.example.premarital.dtos.QuizUserAdviceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizUserAdviceService {
    Page<QuizUserAdviceDTO> getQuizUserAdvices(Pageable pageable);
    void createQuizUserAdvice(QuizUserAdviceDTO dto);
    QuizUserAdviceDTO getQuizUserAdviceById(Long id);
    boolean deleteQuizUserAdviceById(Long id);
    boolean updateQuizUserAdvice(Long id, QuizUserAdviceDTO updatedQuizUserAdviceDTO);
}
