package com.example.premarital.articlePart.service;

import com.example.premarital.articlePart.dto.ArticlePartDTO;
import com.example.premarital.articlePart.model.ArticlePart;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface ArticlePartService {
    PagingResult<ArticlePartDTO> getArticleParts(PaginationRequest request);
    ArticlePartDTO createArticlePart(ArticlePartDTO dto);
    ArticlePart getArticlePartById(Long id);
    boolean deleteArticlePartById(Long id);
    boolean updateArticlePart(Long id, ArticlePartDTO updatedArticlePartDTO);
}
