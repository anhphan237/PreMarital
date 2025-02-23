package com.example.premarital.userQuizHistory.repository;

import com.example.premarital.userQuizHistory.model.UserQuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizHistoryRepository extends JpaRepository<UserQuizHistory, Long> {
}
