package com.example.premarital.services;

import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.models.QuestionOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionOptionService {
    Page<QuestionOptionDTO> getQuestionOptions(Pageable pageable);
    void createQuestionOption(QuestionOptionDTO dto);
    QuestionOptionDTO getQuestionOptionById(Long id);
    boolean deleteQuestionOptionById(Long id);
    boolean updateQuestionOption(Long id, QuestionOptionDTO updatedQuestionOptionDTO);
}
