package com.example.premarital.quizQuestion.model;

import com.example.premarital.Question.model.Question;
import com.example.premarital.quiz.model.Quiz;
import com.example.premarital.userAnswer.model.UserAnswer;
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

    @OneToMany
    @JoinColumn(name = "quiz_question_id", nullable = false)
    private List<UserAnswer> userAnswer;

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
