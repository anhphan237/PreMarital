package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.models.QuestionOption;

public interface QuestionOptionService {
    PagingResult<QuestionOptionDTO> getQuestionOptions(PaginationRequest request);
    QuestionOptionDTO createQuestionOption(QuestionOptionDTO dto);
    QuestionOption getQuestionOptionById(Long id);
    boolean deleteQuestionOptionById(Long id);
    boolean updateQuestionOption(Long id, QuestionOptionDTO updatedQuestionOptionDTO);
}
