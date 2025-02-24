package com.example.premarital.services;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.models.Article;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface ArticleService {
    PagingResult<ArticleDTO> getArticles(PaginationRequest request);
    ArticleDTO createArticle(ArticleDTO dto);
    Article getArticleById(Long id);
    boolean deleteArticleById(Long id);
    boolean updateArticle(Long id, ArticleDTO updatedArticleDTO);
}
