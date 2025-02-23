package com.example.premarital.quizQuestion.repository;

import com.example.premarital.quizQuestion.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
}
