package com.example.premarital.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_answers")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_quiz_history_id", nullable = false)
    private UserQuizHistory userQuizHistory;

    @ManyToOne
    @JoinColumn(name = "quiz_question_id", nullable = false)
    private QuizQuestion quizQuestion;

    public UserAnswer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserQuizHistory getUserQuizHistory() {
        return userQuizHistory;
    }

    public void setUserQuizHistory(UserQuizHistory userQuizHistory) {
        this.userQuizHistory = userQuizHistory;
    }

    public QuizQuestion getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(QuizQuestion quizQuestion) {
        this.quizQuestion = quizQuestion;
    }
}
