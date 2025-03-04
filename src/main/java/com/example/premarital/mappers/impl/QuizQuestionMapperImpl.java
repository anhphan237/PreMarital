package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.mappers.QuizQuestionMapper;
import com.example.premarital.models.Question;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.QuizQuestion;
import org.springframework.stereotype.Component;

@Component
public class QuizQuestionMapperImpl implements QuizQuestionMapper {
    @Override
    public QuizQuestionDTO toDTO(QuizQuestion quizQuestion) {
        if (quizQuestion == null) {
            return null;
        }
        return new QuizQuestionDTO(
                quizQuestion.getId(),
                quizQuestion.getQuestion() != null ? quizQuestion.getQuestion().getId() : null,
                quizQuestion.getQuiz() != null ? quizQuestion.getQuiz().getId() : null
        );
    }

    @Override
    public QuizQuestion toEntity(QuizQuestionDTO quizQuestionDTO) {
        if (quizQuestionDTO == null) {
            return null;
        }
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(quizQuestionDTO.getId());

        if (quizQuestionDTO.getQuestionId() != null) {
            Question question = new Question();
            question.setId(quizQuestionDTO.getQuestionId());
            quizQuestion.setQuestion(question);
        }

        if (quizQuestionDTO.getQuizId() != null) {
            Quiz quiz = new Quiz();
            quiz.setId(quizQuestionDTO.getQuizId());
            quizQuestion.setQuiz(quiz);
        }

        return quizQuestion;
    }
}
