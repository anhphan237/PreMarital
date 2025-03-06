package com.example.premarital.services;

import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.models.UserQuizHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQuizHistoryService {
    Page<UserQuizHistoryDTO> getUserQuizHistories(Pageable pageable);
    void createUserQuizHistory(UserQuizHistoryDTO dto);
    UserQuizHistory getUserQuizHistoryById(Long id);
    boolean deleteUserQuizHistoryById(Long id);
    boolean updateUserQuizHistory(Long id, UserQuizHistoryDTO updatedUserQuizHistoryDTO);
}
