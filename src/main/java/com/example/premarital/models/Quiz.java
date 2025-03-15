package com.example.premarital.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Therapist creator;

    private String title;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    private String status;
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<QuizUserAdvice> quizUserAdvice;

    @ManyToOne
    @JoinColumn(name = "user_quiz_history_id")
    private UserQuizHistory userQuizHistory;
}
