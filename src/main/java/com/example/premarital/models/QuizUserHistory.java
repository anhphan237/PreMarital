package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_user_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizUserHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    
    @Column(nullable = false)
    private Integer score;
    
    @Column(nullable = false)
    private LocalDateTime completedAt;
    
    @Column(columnDefinition = "TEXT")
    private String answersJson;
} 