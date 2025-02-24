package com.example.premarital.article.service;

import com.example.premarital.article.dto.ArticleDTO;
import com.example.premarital.article.model.Article;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface ArticleService {
    PagingResult<ArticleDTO> getArticles(PaginationRequest request);
    ArticleDTO createArticle(ArticleDTO dto);
    Article getArticleById(Long id);
    boolean deleteArticleById(Long id);
    boolean updateArticle(Long id, ArticleDTO updatedArticleDTO);
}
