package com.example.premarital.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quiz_questions")
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "quizQuestion")
    private List<UserAnswer> userAnswer;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public QuizQuestion() {
    }

    public QuizQuestion(Long id, Question question, Quiz quiz) {
        this.id = id;
        this.question = question;
        this.quiz = quiz;
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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
