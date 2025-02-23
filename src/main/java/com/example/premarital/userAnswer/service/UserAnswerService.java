package com.example.premarital.userAnswer.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.userAnswer.dto.UserAnswerDTO;
import com.example.premarital.userAnswer.model.UserAnswer;

public interface UserAnswerService {
    PagingResult<UserAnswerDTO> getUserAnswers(PaginationRequest request);
    UserAnswerDTO createUserAnswer(UserAnswerDTO dto);
    UserAnswer getUserAnswerById(Long id);
    boolean deleteUserAnswerById(Long id);
    boolean updateUserAnswer(Long id, UserAnswerDTO updatedUserAnswerDTO);
}
