package com.example.premarital.repositories;

import com.example.premarital.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
