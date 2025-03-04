package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "quiz_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
