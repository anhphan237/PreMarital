package com.example.premarital.mappers;

import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.models.UserQuizHistory;

public interface UserQuizHistoryMapper {
    UserQuizHistoryDTO toDTO(UserQuizHistory userQuizHistory);
    UserQuizHistory toEntity(UserQuizHistoryDTO userQuizHistoryDTO);
}
