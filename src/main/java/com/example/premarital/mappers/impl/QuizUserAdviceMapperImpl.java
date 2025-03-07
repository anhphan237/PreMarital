package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.mappers.QuizUserAdviceMapper;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.UserQuizHistory;
import org.springframework.stereotype.Component;

@Component
public class QuizUserAdviceMapperImpl implements QuizUserAdviceMapper {
    @Override
    public QuizUserAdviceDTO toDTO(QuizUserAdvice quizUserAdvice) {
        if (quizUserAdvice == null) {
            return null;
        }

        return new QuizUserAdviceDTO(
                quizUserAdvice.getId(),
                quizUserAdvice.getQuiz() != null ? quizUserAdvice.getQuiz().getId() : null, // Chỉ lấy ID
                quizUserAdvice.getAdviceText(),
                quizUserAdvice.getFromPoint(),
                quizUserAdvice.getToPoint(),
                quizUserAdvice.getUserQuizHistory() != null ? quizUserAdvice.getUserQuizHistory().getId() : null,
                quizUserAdvice.getIsActive()
        );
    }

    @Override
    public QuizUserAdvice toEntity(QuizUserAdviceDTO quizUserAdviceDTO) {
        if (quizUserAdviceDTO == null) {
            return null;
        }

        QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
        quizUserAdvice.setId(quizUserAdviceDTO.getId());
        quizUserAdvice.setAdviceText(quizUserAdviceDTO.getAdviceText());
        quizUserAdvice.setFromPoint(quizUserAdviceDTO.getFromPoint());
        quizUserAdvice.setToPoint(quizUserAdviceDTO.getToPoint());
        quizUserAdvice.setIsActive(quizUserAdviceDTO.getIsActive());

        if (quizUserAdviceDTO.getQuizId() != null) {
            Quiz quiz = new Quiz();
            quiz.setId(quizUserAdviceDTO.getQuizId()); // Chỉ gán ID
            quizUserAdvice.setQuiz(quiz);
        }

        if (quizUserAdviceDTO.getUserQuizHistoryId() != null) {
            UserQuizHistory userQuizHistory = new UserQuizHistory();
            userQuizHistory.setId(quizUserAdviceDTO.getUserQuizHistoryId()); // Chỉ gán ID
            quizUserAdvice.setUserQuizHistory(userQuizHistory);
        }

        return quizUserAdvice;
    }

    @Override
    public QuizUserAdvice toEntityWithId(Long id, QuizUserAdviceDTO quizUserAdviceDTO) {
        if (quizUserAdviceDTO == null) {
            return null;
        }

        QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
        quizUserAdvice.setId(id);
        quizUserAdvice.setAdviceText(quizUserAdviceDTO.getAdviceText());
        quizUserAdvice.setFromPoint(quizUserAdviceDTO.getFromPoint());
        quizUserAdvice.setToPoint(quizUserAdviceDTO.getToPoint());
        quizUserAdvice.setIsActive(quizUserAdviceDTO.getIsActive());

        if (quizUserAdviceDTO.getQuizId() != null) {
            Quiz quiz = new Quiz();
            quiz.setId(quizUserAdviceDTO.getQuizId()); // Chỉ gán ID
            quizUserAdvice.setQuiz(quiz);
        }

        if (quizUserAdviceDTO.getUserQuizHistoryId() != null) {
            UserQuizHistory userQuizHistory = new UserQuizHistory();
            userQuizHistory.setId(quizUserAdviceDTO.getUserQuizHistoryId()); // Chỉ gán ID
            quizUserAdvice.setUserQuizHistory(userQuizHistory);
        }

        return quizUserAdvice;
    }
}
