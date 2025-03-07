package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.UserAnswerDTO;
import com.example.premarital.mappers.UserAnswerMapper;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.models.UserAnswer;
import com.example.premarital.models.UserQuizHistory;
import org.springframework.stereotype.Component;

@Component
public class UserAnswerMapperImpl implements UserAnswerMapper {

    @Override
    public UserAnswerDTO toDTO(UserAnswer userAnswer) {
        if (userAnswer == null) return null;
        return new UserAnswerDTO(
                userAnswer.getId(),
                userAnswer.getUserQuizHistory() != null ? userAnswer.getUserQuizHistory().getId() : null,
                userAnswer.getQuizQuestion() != null ? userAnswer.getQuizQuestion().getId() : null,
                userAnswer.getIsActive()
        );
    }

    @Override
    public UserAnswer toEntity(UserAnswerDTO userAnswerDTO) {
        if (userAnswerDTO == null) return null;

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setId(userAnswerDTO.getId());
        userAnswer.setIsActive(userAnswerDTO.getIsActive());

        if (userAnswerDTO.getUserQuizHistoryId() != null) {
            UserQuizHistory userQuizHistory = new UserQuizHistory();
            userQuizHistory.setId(userAnswerDTO.getUserQuizHistoryId());
            userAnswer.setUserQuizHistory(userQuizHistory);
        }

        if (userAnswerDTO.getQuizQuestionId() != null) {
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setId(userAnswerDTO.getQuizQuestionId());
            userAnswer.setQuizQuestion(quizQuestion);
        }

        return userAnswer;
    }

    @Override
    public UserAnswer toEntityWithId(Long id, UserAnswerDTO userAnswerDTO) {
        if (userAnswerDTO == null) return null;

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setId(id);
        userAnswer.setIsActive(userAnswerDTO.getIsActive());

        if (userAnswerDTO.getUserQuizHistoryId() != null) {
            UserQuizHistory userQuizHistory = new UserQuizHistory();
            userQuizHistory.setId(userAnswerDTO.getUserQuizHistoryId());
            userAnswer.setUserQuizHistory(userQuizHistory);
        }

        if (userAnswerDTO.getQuizQuestionId() != null) {
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setId(userAnswerDTO.getQuizQuestionId());
            userAnswer.setQuizQuestion(quizQuestion);
        }

        return userAnswer;
    }
}
