package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.UserQuizHistoryMapper;
import com.example.premarital.models.UserQuizHistory;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.UserQuizHistoryRepository;
import com.example.premarital.services.UserQuizHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserQuizHistoryServiceImpl implements UserQuizHistoryService {
    private final UserQuizHistoryRepository userQuizHistoryRepository;
    private final UserQuizHistoryMapper userQuizHistoryMapper;

    public UserQuizHistoryServiceImpl(UserQuizHistoryRepository userQuizHistoryRepository, UserQuizHistoryMapper userQuizHistoryMapper) {
        this.userQuizHistoryRepository = userQuizHistoryRepository;
        this.userQuizHistoryMapper = userQuizHistoryMapper;
    }

    @Override
    public Page<UserQuizHistoryDTO> getUserQuizHistorys(Pageable pageable) {
        Page<UserQuizHistory> entities = userQuizHistoryRepository.findAll(pageable);
        Page<UserQuizHistoryDTO> dtoPage = entities.map(new Function<UserQuizHistory, UserQuizHistoryDTO>() {

            @Override
            public UserQuizHistoryDTO apply(UserQuizHistory userQuizHistory) {
                UserQuizHistoryDTO dto = userQuizHistoryMapper.toDTO(userQuizHistory);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createUserQuizHistory(UserQuizHistoryDTO dto) {
        UserQuizHistory userQuizHistory = userQuizHistoryMapper.toEntity(dto);
        userQuizHistoryRepository.save(userQuizHistory);
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
