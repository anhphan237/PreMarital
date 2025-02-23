package com.example.premarital.quizUserAdvice.dto;

import com.example.premarital.quiz.model.Quiz;

public class QuizUserAdviceDTO {
    private Long id;
    private Quiz quiz;
    private String adviceText;
    private int fromPoint;
    private int toPoint;
    private Long userQuizHistoryId;

    public QuizUserAdviceDTO() {
    }

    public QuizUserAdviceDTO(Long id, Quiz quiz, String adviceText, int fromPoint, int toPoint, Long userQuizHistoryId) {
        this.id = id;
        this.quiz = quiz;
        this.adviceText = adviceText;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.userQuizHistoryId = userQuizHistoryId;
    }

    public Long getUserQuizHistoryId() {
        return userQuizHistoryId;
    }

    public void setUserQuizHistoryId(Long userQuizHistoryId) {
        this.userQuizHistoryId = userQuizHistoryId;
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
