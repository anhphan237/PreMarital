package com.example.premarital.repositories;

import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizUserAdviceRepository extends JpaRepository<QuizUserAdvice, Long> {
}
