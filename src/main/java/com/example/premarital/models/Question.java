package com.example.premarital.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<QuizQuestion> quizQuestion;

    private String questionText;
    private Date createdAt;
    private Date updatedAt;
    private String forGender;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<QuestionOption> questionOption;

    public Question() {
    }

    public Question(Long id, List<QuizQuestion> quizQuestion, String questionText, Date createdAt, Date updatedAt, String forGender, List<QuestionOption> questionOption) {
        this.id = id;
        this.quizQuestion = quizQuestion;
        this.questionText = questionText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.forGender = forGender;
        this.questionOption = questionOption;
    }

    public List<QuestionOption> getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(List<QuestionOption> questionOption) {
        this.questionOption = questionOption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<QuizQuestion> getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(List<QuizQuestion> quizQuestion) {
        this.quizQuestion = quizQuestion;
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
