package com.example.premarital.questionOption.dto;

import com.example.premarital.Question.model.Question;

public class QuestionOptionDTO {
    private Long id;
    private Question question;
    private String optionText;
    private int point;

    public QuestionOptionDTO() {
    }

    public QuestionOptionDTO(Long id, Question question, String optionText, int point) {
        this.id = id;
        this.question = question;
        this.optionText = optionText;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
