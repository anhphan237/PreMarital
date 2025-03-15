package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user_quiz_histories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuizHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "userQuizHistory")
    private List<Quiz> quiz;

    private int quizPoint;

    @OneToOne(mappedBy = "userQuizHistory")
    private QuizUserAdvice quizUserAdvice;

    @OneToMany(mappedBy = "userQuizHistory")
    private List<UserAnswer> userAnswer;

    @Column(name = "is_active")
    private Boolean isActive;
}
