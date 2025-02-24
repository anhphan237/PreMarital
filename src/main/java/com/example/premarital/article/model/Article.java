package com.example.premarital.article.model;

import com.example.premarital.articlePart.model.ArticlePart;
import com.example.premarital.category.model.Category;
import com.example.premarital.therapist.model.Therapist;
import com.example.premarital.user.model.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String referencePath;
    private String status;

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

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReferencePath() {
        return referencePath;
    }

    public void setReferencePath(String referencePath) {
        this.referencePath = referencePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getApprovedAdmin() {
        return approvedAdmin;
    }

    public void setApprovedAdmin(User approvedAdmin) {
        this.approvedAdmin = approvedAdmin;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Article> getReferenceArticles() {
        return referenceArticles;
    }

    public void setReferenceArticles(List<Article> referenceArticles) {
        this.referenceArticles = referenceArticles;
    }

    public Article getReferenceArticle() {
        return referenceArticle;
    }

    public void setReferenceArticle(Article referenceArticle) {
        this.referenceArticle = referenceArticle;
    }

    public List<ArticlePart> getArticleParts() {
        return articleParts;
    }

    public void setArticleParts(List<ArticlePart> articleParts) {
        this.articleParts = articleParts;
    }
}
