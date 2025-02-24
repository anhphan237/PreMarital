package com.example.premarital.services.impl;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.models.ArticlePart;
import com.example.premarital.repositories.ArticlePartRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.services.ArticlePartService;
import org.springframework.stereotype.Service;

@Service
public class ArticlePartServiceImpl implements ArticlePartService {
    private final ArticlePartRepository articlePartRepository;

    public ArticlePartServiceImpl(ArticlePartRepository articlePartRepository) {
        this.articlePartRepository = articlePartRepository;
    }

    @Override
    public PagingResult<ArticlePartDTO> getArticleParts(PaginationRequest request) {
        return null;
    }

    @Override
    public ArticlePartDTO createArticlePart(ArticlePartDTO dto) {
        return null;
    }

    @Override
    public ArticlePart getArticlePartById(Long id) {
        return null;
    }

    @Override
    public boolean deleteArticlePartById(Long id) {
        return false;
    }

    @Override
    public boolean updateArticlePart(Long id, ArticlePartDTO updatedArticlePartDTO) {
        return false;
    }
}
