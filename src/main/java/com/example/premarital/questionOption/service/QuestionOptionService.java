package com.example.premarital.questionOption.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.questionOption.dto.QuestionOptionDTO;
import com.example.premarital.questionOption.model.QuestionOption;

public interface QuestionOptionService {
    PagingResult<QuestionOptionDTO> getQuestionOptions(PaginationRequest request);
    QuestionOptionDTO createQuestionOption(QuestionOptionDTO dto);
    QuestionOption getQuestionOptionById(Long id);
    boolean deleteQuestionOptionById(Long id);
    boolean updateQuestionOption(Long id, QuestionOptionDTO updatedQuestionOptionDTO);
}
