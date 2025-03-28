package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_quiz_histories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuizHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "quizId", nullable = false)
    Quiz quiz;

    private int quizPoint;

    @ManyToOne
    @JoinColumn(name = "quizUserAdviceId", nullable = false)
    private QuizUserAdvice quizUserAdvice;

    @OneToMany(mappedBy = "userQuizHistory")
    private List<UserAnswer> userAnswer;

    private LocalDateTime attemptTime;

    @PrePersist
    private void prePersist() {
        this.attemptTime = LocalDateTime.now();
    }
}
