package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.mappers.QuizQuestionMapper;
import com.example.premarital.models.Category;
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
                quizQuestion.getQuiz() != null ? quizQuestion.getQuiz().getId() : null,
                quizQuestion.getCategory() != null ? quizQuestion.getCategory().getId() : null,
                quizQuestion.getIsActive()
        );
    }

    @Override
    public QuizQuestion toEntity(QuizQuestionDTO quizQuestionDTO) {
        if (quizQuestionDTO == null) {
            return null;
        }
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(quizQuestionDTO.getId());
        quizQuestion.setIsActive(quizQuestionDTO.getIsActive());

        if (quizQuestionDTO.getQuestionId() != null) {
            Question question = new Question();
            question.setId(quizQuestionDTO.getQuestionId());
            quizQuestion.setQuestion(question);
        }

        if(quizQuestionDTO.getCategoryId() != null) {
            Category category = new Category();
            category.setId(quizQuestionDTO.getCategoryId());
            quizQuestion.setCategory(category);
        }

        if (quizQuestionDTO.getQuizId() != null) {
            Quiz quiz = new Quiz();
            quiz.setId(quizQuestionDTO.getQuizId());
            quizQuestion.setQuiz(quiz);
        }

        return quizQuestion;
    }

    @Override
    public QuizQuestion toEntityWithId(Long id, QuizQuestionDTO quizQuestionDTO) {
        if (quizQuestionDTO == null) {
            return null;
        }
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(id);
        quizQuestion.setIsActive(quizQuestionDTO.getIsActive());

        if (quizQuestionDTO.getQuestionId() != null) {
            Question question = new Question();
            question.setId(quizQuestionDTO.getQuestionId());
            quizQuestion.setQuestion(question);
        }

        if(quizQuestionDTO.getCategoryId() != null) {
            Category category = new Category();
            category.setId(quizQuestionDTO.getCategoryId());
            quizQuestion.setCategory(category);
        }

        if (quizQuestionDTO.getQuizId() != null) {
            Quiz quiz = new Quiz();
            quiz.setId(quizQuestionDTO.getQuizId());
            quizQuestion.setQuiz(quiz);
        }

        return quizQuestion;
    }
}
