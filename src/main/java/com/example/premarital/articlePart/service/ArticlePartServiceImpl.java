package com.example.premarital.articlePart.service;

import com.example.premarital.articlePart.dto.ArticlePartDTO;
import com.example.premarital.articlePart.model.ArticlePart;
import com.example.premarital.articlePart.repository.ArticlePartRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
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
