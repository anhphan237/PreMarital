package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article_parts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private int orderIndex;
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
}
