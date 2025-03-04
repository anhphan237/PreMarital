package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_quiz_history_id", nullable = false)
    private UserQuizHistory userQuizHistory;

    @ManyToOne
    @JoinColumn(name = "quiz_question_id", nullable = false)
    private QuizQuestion quizQuestion;
}
