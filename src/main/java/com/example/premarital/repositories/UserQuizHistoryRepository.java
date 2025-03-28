package com.example.premarital.repositories;

import com.example.premarital.models.UserQuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuizHistoryRepository extends JpaRepository<UserQuizHistory, Long> {

    List<UserQuizHistory> findAllByUser_Email(String email);
}
