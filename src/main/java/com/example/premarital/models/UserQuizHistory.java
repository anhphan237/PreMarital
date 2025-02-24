package com.example.premarital.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_quiz_histories")
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

    public UserQuizHistory() {
    }

    public UserQuizHistory(Long id, User user, List<Quiz> quiz, int quizPoint, QuizUserAdvice quizUserAdvice, List<UserAnswer> userAnswer) {
        this.id = id;
        this.user = user;
        this.quiz = quiz;
        this.quizPoint = quizPoint;
        this.quizUserAdvice = quizUserAdvice;
        this.userAnswer = userAnswer;
    }
}
