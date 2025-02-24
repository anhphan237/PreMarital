package com.example.premarital.services.impl;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.models.Article;
import com.example.premarital.repositories.ArticleRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.services.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public PagingResult<ArticleDTO> getArticles(PaginationRequest request) {
        return null;
    }

    @Override
    public ArticleDTO createArticle(ArticleDTO dto) {
        return null;
    }

    @Override
    public Article getArticleById(Long id) {
        return null;
    }

    @Override
    public boolean deleteArticleById(Long id) {
        return false;
    }

    @Override
    public boolean updateArticle(Long id, ArticleDTO updatedArticleDTO) {
        return false;
    }
}
