package com.example.premarital.userQuizHistory.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.userQuizHistory.dto.UserQuizHistoryDTO;
import com.example.premarital.userQuizHistory.model.UserQuizHistory;

public interface UserQuizHistoryService {
    PagingResult<UserQuizHistoryDTO> getUserQuizHistorys(PaginationRequest request);
    UserQuizHistoryDTO createUserQuizHistory(UserQuizHistoryDTO dto);
    UserQuizHistory getUserQuizHistoryById(Long id);
    boolean deleteUserQuizHistoryById(Long id);
    boolean updateUserQuizHistory(Long id, UserQuizHistoryDTO updatedUserQuizHistoryDTO);
}
