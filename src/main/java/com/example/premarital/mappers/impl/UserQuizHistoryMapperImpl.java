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
                userQuizHistory.getQuizUserAdvice() != null ? userQuizHistory.getQuizUserAdvice().getId() : null // Chỉ lấy ID
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

        if (userQuizHistoryDTO.getUserId() != null) {
            User user = new User();
            user.setId(userQuizHistoryDTO.getUserId());  // Chỉ gán ID, không fetch toàn bộ object
            userQuizHistory.setUser(user);
        }

        if (userQuizHistoryDTO.getQuizUserAdviceId() != null) {
            QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
            quizUserAdvice.setId(userQuizHistoryDTO.getQuizUserAdviceId());  // Chỉ gán ID
            userQuizHistory.setQuizUserAdvice(quizUserAdvice);
        }

        return userQuizHistory;
    }
}
