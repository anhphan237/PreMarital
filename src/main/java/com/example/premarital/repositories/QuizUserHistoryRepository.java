package com.example.premarital.repositories;

import com.example.premarital.models.QuizUserHistory;
import com.example.premarital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizUserHistoryRepository extends JpaRepository<QuizUserHistory, Long> {

    List<QuizUserHistory> findAllByUserOrderByCompletedAtDesc(User user);

    boolean existsByUserAndQuizQuizId(User user, Long quizId);
} 