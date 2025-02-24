package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.models.UserQuizHistory;

public interface UserQuizHistoryService {
    PagingResult<UserQuizHistoryDTO> getUserQuizHistorys(PaginationRequest request);
    UserQuizHistoryDTO createUserQuizHistory(UserQuizHistoryDTO dto);
    UserQuizHistory getUserQuizHistoryById(Long id);
    boolean deleteUserQuizHistoryById(Long id);
    boolean updateUserQuizHistory(Long id, UserQuizHistoryDTO updatedUserQuizHistoryDTO);
}
