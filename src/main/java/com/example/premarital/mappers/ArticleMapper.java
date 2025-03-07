package com.example.premarital.mappers;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.models.Article;

public interface ArticleMapper {
    ArticleDTO toDTO(Article article);
    Article toEntity(ArticleDTO dto);
    Article toEntityWithId(Long id, ArticleDTO dto);
}
