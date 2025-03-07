package com.example.premarital.services;

import com.example.premarital.dtos.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    Page<ArticleDTO> getArticles(Pageable pageable);
    void createArticle(ArticleDTO dto);
    ArticleDTO getArticleById(Long id);
    boolean deleteArticleById(Long id);
    boolean updateArticle(Long id, ArticleDTO updatedArticleDTO);
}
