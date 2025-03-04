package com.example.premarital.mappers;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.models.ArticlePart;

public interface ArticlePartMapper {
    ArticlePartDTO toDTO(ArticlePart articlePart);
    ArticlePart toEntity(ArticlePartDTO articlePartDTO);
}
