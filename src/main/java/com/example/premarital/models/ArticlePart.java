package com.example.premarital.models;

import jakarta.persistence.*;

@Entity
@Table(name = "article_parts")
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

    public ArticlePart() {
    }

    public ArticlePart(Long id, String title, String content, int orderIndex, String imageLink, Article article) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.orderIndex = orderIndex;
        this.imageLink = imageLink;
        this.article = article;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
