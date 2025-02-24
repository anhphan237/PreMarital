package com.example.premarital.dtos;

public class QuizCategoryDTO {
    private Long id;
    private Long categoryId;
    private Long quizId;

    public QuizCategoryDTO() {
    }

    public QuizCategoryDTO(Long id, Long categoryId, Long quizId) {
        this.id = id;
        this.categoryId = categoryId;
        this.quizId = quizId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
