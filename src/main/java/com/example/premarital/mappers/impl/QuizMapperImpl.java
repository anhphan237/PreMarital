package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.mappers.QuizMapper;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.UserQuizHistory;
import com.example.premarital.repositories.QuizUserAdviceRepository;
import com.example.premarital.repositories.TherapistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class QuizMapperImpl implements QuizMapper {
    private final TherapistRepository therapistRepository;
    private final QuizUserAdviceRepository quizUserAdviceRepository;

    public QuizMapperImpl(TherapistRepository therapistRepository, QuizUserAdviceRepository quizUserAdviceRepository) {
        this.therapistRepository = therapistRepository;
        this.quizUserAdviceRepository = quizUserAdviceRepository;
    }

    @Override
    public QuizDTO toDTO(Quiz quiz) {
        if (quiz == null) {
            return null;
        }
        return new QuizDTO(
                quiz.getId(),
                (quiz.getCreator() != null) ? quiz.getCreator().getId() : null,
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getCreatedAt(),
                quiz.getUpdatedAt(),
                quiz.getStatus(),
                quiz.getIsActive(),
                quiz.getUserQuizHistory() == null ? null : quiz.getUserQuizHistory().getId()
        );
    }

    @Override
    public Quiz toEntity(QuizDTO quizDTO) {
        if (quizDTO == null) {
            return null;
        }

        Quiz quiz = new Quiz();
        quiz.setId(quizDTO.getId());

        Therapist creator = therapistRepository.findById(quizDTO.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));
        quiz.setCreator(creator);

        quiz.setTitle(quizDTO.getTitle());
        quiz.setDescription(quizDTO.getDescription());
        quiz.setCreatedAt(quizDTO.getCreatedAt());
        quiz.setUpdatedAt(quizDTO.getUpdatedAt());
        quiz.setStatus(quizDTO.getStatus());
        quiz.setIsActive(quizDTO.getIsActive());

        if (quizDTO.getUserQuizHistoryId() != null) {
            UserQuizHistory userQuizHistory = new UserQuizHistory();
            userQuizHistory.setId(quizDTO.getUserQuizHistoryId());
            quiz.setUserQuizHistory(userQuizHistory);
        }

        return quiz;
    }

    @Override
    public Quiz toEntityWithId(Long id, QuizDTO quizDTO) {
        if (quizDTO == null) {
            return null;
        }

        Quiz quiz = new Quiz();
        quiz.setId(id);

        Therapist creator = therapistRepository.findById(quizDTO.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));
        quiz.setCreator(creator);

        quiz.setTitle(quizDTO.getTitle());
        quiz.setDescription(quizDTO.getDescription());
        quiz.setCreatedAt(quizDTO.getCreatedAt());
        quiz.setUpdatedAt(quizDTO.getUpdatedAt());
        quiz.setStatus(quizDTO.getStatus());
        quiz.setIsActive(quizDTO.getIsActive());

        if (quizDTO.getUserQuizHistoryId() != null) {
            UserQuizHistory userQuizHistory = new UserQuizHistory();
            userQuizHistory.setId(quizDTO.getUserQuizHistoryId());
            quiz.setUserQuizHistory(userQuizHistory);
        }

        return quiz;
    }

}
