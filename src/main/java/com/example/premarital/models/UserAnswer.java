package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_quiz_history_id", nullable = false)
    private UserQuizHistory userQuizHistory;

    @ManyToOne
    @JoinColumn(name = "question_option_id", nullable = false)
    private QuestionOption questionOption;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
