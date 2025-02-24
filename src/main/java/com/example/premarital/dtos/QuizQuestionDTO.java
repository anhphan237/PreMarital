package com.example.premarital.dtos;

public class QuizQuestionDTO {
    private Long id;
    private Long questionId;
    private Long quizId;

    public QuizQuestionDTO() {
    }

    public QuizQuestionDTO(Long id, Long questionId, Long quizId) {
        this.id = id;
        this.questionId = questionId;
        this.quizId = quizId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
