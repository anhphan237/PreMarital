package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<QuizQuestion> quizQuestion;

    private String questionText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String forGender;
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<QuestionOption> questionOption;
}
