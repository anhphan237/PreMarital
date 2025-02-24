package com.example.premarital.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Therapist creator;

    private String title;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;
    private String status;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<QuizUserAdvice> quizUserAdvice;

    @ManyToOne
    @JoinColumn(name = "user_quiz_history_id", nullable = false)
    private UserQuizHistory userQuizHistory;

    public Quiz() {
    }

    public Quiz(Long id, Therapist creator, String title, String description, Date createdAt, Date updatedAt, String status, List<QuizUserAdvice> quizUserAdvice, UserQuizHistory userQuizHistory) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.quizUserAdvice = quizUserAdvice;
        this.userQuizHistory = userQuizHistory;
    }

    public UserQuizHistory getUserQuizHistory() {
        return userQuizHistory;
    }

    public void setUserQuizHistory(UserQuizHistory userQuizHistory) {
        this.userQuizHistory = userQuizHistory;
    }

    public List<QuizUserAdvice> getQuizUserAdvice() {
        return quizUserAdvice;
    }

    public void setQuizUserAdvice(List<QuizUserAdvice> quizUserAdvice) {
        this.quizUserAdvice = quizUserAdvice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Therapist getCreator() {
        return creator;
    }

    public void setCreator(Therapist creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
