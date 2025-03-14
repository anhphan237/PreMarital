package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String referencePath;
    private String status;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "approved_admin_id")
    private User approvedAdmin;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "referenceArticle")
    private List<Article> referenceArticles;

    @ManyToOne
    @JoinColumn(name = "reference_article_id")
    private Article referenceArticle;

    @OneToMany(mappedBy = "article")
    private List<ArticlePart> articleParts;
}
