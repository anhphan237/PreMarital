package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private Date createdAt;
    private Date updatedAt;
    private String forGender;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<QuestionOption> questionOption;
}
