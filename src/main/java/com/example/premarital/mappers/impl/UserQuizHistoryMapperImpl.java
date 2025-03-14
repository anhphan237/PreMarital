package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.mappers.UserQuizHistoryMapper;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.User;
import com.example.premarital.models.UserQuizHistory;
import org.springframework.stereotype.Component;

@Component
public class UserQuizHistoryMapperImpl implements UserQuizHistoryMapper {
    @Override
    public UserQuizHistoryDTO toDTO(UserQuizHistory userQuizHistory) {
        if (userQuizHistory == null) {
            return null;
        }

        return new UserQuizHistoryDTO(
                userQuizHistory.getId(),
                userQuizHistory.getUser() != null ? userQuizHistory.getUser().getId() : null,  // Chỉ lấy ID
                userQuizHistory.getQuizPoint(),
                userQuizHistory.getQuizUserAdvice() != null ? userQuizHistory.getQuizUserAdvice().getId() : null,
                userQuizHistory.getIsActive()
        );
    }

    @Override
    public UserQuizHistory toEntity(UserQuizHistoryDTO userQuizHistoryDTO) {
        if (userQuizHistoryDTO == null) {
            return null;
        }

        UserQuizHistory userQuizHistory = new UserQuizHistory();
        userQuizHistory.setId(userQuizHistoryDTO.getId());
        userQuizHistory.setQuizPoint(userQuizHistoryDTO.getQuizPoint());
        userQuizHistory.setIsActive(userQuizHistoryDTO.getIsActive());

        if (userQuizHistoryDTO.getUserId() != null) {
            User user = new User();
            user.setId(userQuizHistoryDTO.getUserId());
            userQuizHistory.setUser(user);
        }

        if (userQuizHistoryDTO.getQuizUserAdviceId() != null) {
            QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
            quizUserAdvice.setId(userQuizHistoryDTO.getQuizUserAdviceId());  // Chỉ gán ID
            userQuizHistory.setQuizUserAdvice(quizUserAdvice);
        }

        return userQuizHistory;
    }

    @Override
    public UserQuizHistory toEntityWithId(Long id, UserQuizHistoryDTO userQuizHistoryDTO) {
        if (userQuizHistoryDTO == null) {
            return null;
        }

        UserQuizHistory userQuizHistory = new UserQuizHistory();
        userQuizHistory.setId(id);
        userQuizHistory.setQuizPoint(userQuizHistoryDTO.getQuizPoint());
        userQuizHistory.setIsActive(userQuizHistoryDTO.getIsActive());

        if (userQuizHistoryDTO.getUserId() != null) {
            User user = new User();
            user.setId(userQuizHistoryDTO.getUserId());
            userQuizHistory.setUser(user);
        }

        if (userQuizHistoryDTO.getQuizUserAdviceId() != null) {
            QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
            quizUserAdvice.setId(userQuizHistoryDTO.getQuizUserAdviceId());
            userQuizHistory.setQuizUserAdvice(quizUserAdvice);
        }

        return userQuizHistory;
    }
}
