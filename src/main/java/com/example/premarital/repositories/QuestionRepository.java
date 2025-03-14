package com.example.premarital.repositories;

import com.example.premarital.models.Question;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findQuestionsByIsActiveTrue(Pageable pageable);
}
