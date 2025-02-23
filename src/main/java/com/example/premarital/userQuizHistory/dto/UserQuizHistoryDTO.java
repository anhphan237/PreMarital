package com.example.premarital.userQuizHistory.dto;

public class UserQuizHistoryDTO {
    private Long id;
    private Long userId;
    private int quizPoint;
    private Long quizUserAdviceId;

    public UserQuizHistoryDTO() {
    }

    public UserQuizHistoryDTO(Long id, Long userId, int quizPoint, Long quizUserAdviceId) {
        this.id = id;
        this.userId = userId;
        this.quizPoint = quizPoint;
        this.quizUserAdviceId = quizUserAdviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuizPoint() {
        return quizPoint;
    }

    public void setQuizPoint(int quizPoint) {
        this.quizPoint = quizPoint;
    }

    public Long getQuizUserAdviceId() {
        return quizUserAdviceId;
    }

    public void setQuizUserAdviceId(Long quizUserAdviceId) {
        this.quizUserAdviceId = quizUserAdviceId;
    }
}
