package com.example.premarital.Question.repository;

import com.example.premarital.Question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
