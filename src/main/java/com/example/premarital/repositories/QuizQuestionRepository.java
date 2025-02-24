package com.example.premarital.repositories;

import com.example.premarital.models.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
}
