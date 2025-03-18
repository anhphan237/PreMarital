package com.example.premarital.services.impl;

import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.mappers.UserQuizHistoryMapper;
import com.example.premarital.models.UserQuizHistory;
import com.example.premarital.repositories.UserQuizHistoryRepository;
import com.example.premarital.services.UserQuizHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserQuizHistoryServiceImpl implements UserQuizHistoryService {
    private final UserQuizHistoryRepository userQuizHistoryRepository;
    private final UserQuizHistoryMapper userQuizHistoryMapper;

    @Override
    public Page<UserQuizHistoryDTO> getUserQuizHistories(Pageable pageable) {
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
    public UserQuizHistoryDTO getUserQuizHistoryById(Long id) {
        return userQuizHistoryMapper.toDTO(userQuizHistoryRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteUserQuizHistoryById(Long id) {
        return userQuizHistoryRepository.findById(id).map(userQuizHistory -> {
            userQuizHistory.setIsActive(false);
            userQuizHistoryRepository.save(userQuizHistory);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateUserQuizHistory(Long id, UserQuizHistoryDTO updatedUserQuizHistoryDTO) {
        return userQuizHistoryRepository.findById(id).map(userQuizHistory -> {
            UserQuizHistory updatedUserQuizHistory = userQuizHistoryMapper.toEntityWithId(id, updatedUserQuizHistoryDTO);
            userQuizHistoryRepository.save(updatedUserQuizHistory);
            return true;
        }).orElse(false);
    }
}
