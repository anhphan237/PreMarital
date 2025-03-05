package com.example.premarital.services;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.models.ArticlePart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticlePartService {
    Page<ArticlePartDTO> getArticleParts(Pageable pageable);
    void createArticlePart(ArticlePartDTO dto);
    ArticlePart getArticlePartById(Long id);
    boolean deleteArticlePartById(Long id);
    boolean updateArticlePart(Long id, ArticlePartDTO updatedArticlePartDTO);
}
