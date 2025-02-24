package com.example.premarital.article.service;

import com.example.premarital.article.dto.ArticleDTO;
import com.example.premarital.article.model.Article;
import com.example.premarital.article.repository.ArticleRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
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
