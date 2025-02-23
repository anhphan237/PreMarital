package com.example.premarital.Question.dto;

import java.util.Date;

public class QuestionDTO {
    private Long id;
    private String questionText;
    private Date createdAt;
    private Date updatedAt;
    private String forGender;

    public QuestionDTO() {
    }

    public QuestionDTO(Long id, String questionText, Date createdAt, Date updatedAt, String forGender) {
        this.id = id;
        this.questionText = questionText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.forGender = forGender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getForGender() {
        return forGender;
    }

    public void setForGender(String forGender) {
        this.forGender = forGender;
    }
}
