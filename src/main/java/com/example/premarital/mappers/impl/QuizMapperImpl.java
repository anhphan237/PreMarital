package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.mappers.QuizMapper;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.Therapist;
import org.springframework.stereotype.Component;

@Component
public class QuizMapperImpl implements QuizMapper {
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
                quiz.getStatus()
        );
    }

    @Override
    public Quiz toEntity(QuizDTO quizDTO) {
        if (quizDTO == null) {
            return null;
        }
        Quiz quiz = new Quiz();
        quiz.setId(quizDTO.getId());

        // Không đặt trực tiếp Creator vì DTO chỉ chứa `creatorId`
        Therapist creator = new Therapist();
        creator.setId(quizDTO.getCreatorId());
        quiz.setCreator(creator);

        quiz.setTitle(quizDTO.getTitle());
        quiz.setDescription(quizDTO.getDescription());
        quiz.setCreatedAt(quizDTO.getCreatedAt());
        quiz.setUpdatedAt(quizDTO.getUpdatedAt());
        quiz.setStatus(quizDTO.getStatus());

        return quiz;
    }
}
