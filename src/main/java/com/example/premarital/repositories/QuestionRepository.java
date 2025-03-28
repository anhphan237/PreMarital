package com.example.premarital.repositories;

import com.example.premarital.models.Question;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    List<Question> findByForGender(Question.QuestionGender gender);
    
    @Query("SELECT q FROM Question q WHERE q.forGender = :gender OR q.forGender = 'BOTH'")
    List<Question> findByGenderIncludingBoth(@Param("gender") Question.QuestionGender gender);
    
    List<Question> findByQuestionTextContainingIgnoreCase(String searchText);
    
    @Query("SELECT DISTINCT qq.question FROM QuizQuestion qq WHERE qq.quiz.therapist.id = :therapistId")
    List<Question> findQuestionsByTherapistId(@Param("therapistId") Long therapistId);
}
