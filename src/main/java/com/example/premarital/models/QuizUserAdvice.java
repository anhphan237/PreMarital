package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz_user_advices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizUserAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizUserAdviceId;

    private String adviceText;

    private int fromPoint;
    private int toPoint;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
}
