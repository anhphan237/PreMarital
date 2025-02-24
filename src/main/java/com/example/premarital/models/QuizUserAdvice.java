package com.example.premarital.models;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_user_advices")
public class QuizUserAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    private String adviceText;

    private int fromPoint;
    private int toPoint;

    @OneToOne
    @JoinColumn(name = "user_quiz_history_id", nullable = false)
    private UserQuizHistory userQuizHistory;

    public QuizUserAdvice() {
    }

    public QuizUserAdvice(Long id, Quiz quiz, String adviceText, int fromPoint, int toPoint, UserQuizHistory userQuizHistory) {
        this.id = id;
        this.quiz = quiz;
        this.adviceText = adviceText;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.userQuizHistory = userQuizHistory;
    }

    public UserQuizHistory getUserQuizHistory() {
        return userQuizHistory;
    }

    public void setUserQuizHistory(UserQuizHistory userQuizHistory) {
        this.userQuizHistory = userQuizHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getAdviceText() {
        return adviceText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }

    public int getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(int fromPoint) {
        this.fromPoint = fromPoint;
    }

    public int getToPoint() {
        return toPoint;
    }

    public void setToPoint(int toPoint) {
        this.toPoint = toPoint;
    }
}
