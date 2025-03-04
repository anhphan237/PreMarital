package com.example.premarital.mappers;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.models.Article;

public interface ArticleMapper {
    Article toEntity(ArticleDTO dto);

    ArticleDTO toDto(Article article);
}
