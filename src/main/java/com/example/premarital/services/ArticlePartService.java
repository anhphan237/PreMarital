package com.example.premarital.services;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.models.ArticlePart;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface ArticlePartService {
    PagingResult<ArticlePartDTO> getArticleParts(PaginationRequest request);
    ArticlePartDTO createArticlePart(ArticlePartDTO dto);
    ArticlePart getArticlePartById(Long id);
    boolean deleteArticlePartById(Long id);
    boolean updateArticlePart(Long id, ArticlePartDTO updatedArticlePartDTO);
}
