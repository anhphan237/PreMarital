package com.example.premarital.quizCategory.model;

import com.example.premarital.category.model.Category;
import com.example.premarital.quiz.model.Quiz;
import jakarta.persistence.*;

@Entity
@Table(name = "quiz_categories")
public class QuizCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    public QuizCategory(Long id, Category category, Quiz quiz) {
        this.id = id;
        this.category = category;
        this.quiz = quiz;
    }

    public QuizCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
