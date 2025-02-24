package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.models.UserQuizHistory;
import com.example.premarital.repositories.UserQuizHistoryRepository;
import com.example.premarital.services.UserQuizHistoryService;
import org.springframework.stereotype.Service;

@Service
public class UserQuizHistoryServiceImpl implements UserQuizHistoryService {
    private final UserQuizHistoryRepository userQuizHistoryRepository;

    public UserQuizHistoryServiceImpl(UserQuizHistoryRepository userQuizHistoryRepository) {
        this.userQuizHistoryRepository = userQuizHistoryRepository;
    }

    @Override
    public PagingResult<UserQuizHistoryDTO> getUserQuizHistorys(PaginationRequest request) {
        return null;
    }

    @Override
    public UserQuizHistoryDTO createUserQuizHistory(UserQuizHistoryDTO dto) {
        return null;
    }

    @Override
    public UserQuizHistory getUserQuizHistoryById(Long id) {
        return null;
    }

    @Override
    public boolean deleteUserQuizHistoryById(Long id) {
        return false;
    }

    @Override
    public boolean updateUserQuizHistory(Long id, UserQuizHistoryDTO updatedUserQuizHistoryDTO) {
        return false;
    }
}
