package com.example.premarital.services;

import com.example.premarital.dtos.ArticlePartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticlePartService {
    Page<ArticlePartDTO> getArticleParts(Pageable pageable);
    void createArticlePart(ArticlePartDTO dto);
    ArticlePartDTO getArticlePartById(Long id);
    boolean deleteArticlePartById(Long id);
    boolean updateArticlePart(Long id, ArticlePartDTO updatedArticlePartDTO);
}
