package com.example.premarital.userAnswer.dto;

public class UserAnswerDTO {
    private Long id;
    private Long userQuizHistoryId;
    private Long quizQuestionId;

    public UserAnswerDTO() {
    }

    public UserAnswerDTO(Long id, Long userQuizHistoryId, Long quizQuestionId) {
        this.id = id;
        this.userQuizHistoryId = userQuizHistoryId;
        this.quizQuestionId = quizQuestionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserQuizHistoryId() {
        return userQuizHistoryId;
    }

    public void setUserQuizHistoryId(Long userQuizHistoryId) {
        this.userQuizHistoryId = userQuizHistoryId;
    }

    public Long getQuizQuestionId() {
        return quizQuestionId;
    }

    public void setQuizQuestionId(Long quizQuestionId) {
        this.quizQuestionId = quizQuestionId;
    }
}
