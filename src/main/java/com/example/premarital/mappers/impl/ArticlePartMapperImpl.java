package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.mappers.ArticlePartMapper;
import com.example.premarital.models.Article;
import com.example.premarital.models.ArticlePart;
import com.example.premarital.repositories.ArticleRepository;
import org.springframework.stereotype.Component;

@Component
public class ArticlePartMapperImpl implements ArticlePartMapper {
    private final ArticleRepository articleRepository;

    public ArticlePartMapperImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticlePartDTO toDTO(ArticlePart articlePart) {
        if (articlePart == null) {
            return null;
        }

        return new ArticlePartDTO(
                articlePart.getId(),
                articlePart.getTitle(),
                articlePart.getContent(),
                articlePart.getOrderIndex(),
                articlePart.getImageLink(),
                (articlePart.getArticle() != null) ? articlePart.getArticle().getId() : null
        );
    }

    @Override
    public ArticlePart toEntity(ArticlePartDTO articlePartDTO) {
        if (articlePartDTO == null) {
            return null;
        }

        ArticlePart articlePart = new ArticlePart();
        articlePart.setId(articlePartDTO.getId());
        articlePart.setTitle(articlePartDTO.getTitle());
        articlePart.setContent(articlePartDTO.getContent());
        articlePart.setOrderIndex(articlePartDTO.getOrderIndex());
        articlePart.setImageLink(articlePartDTO.getImageLink());

        // Nếu có articleId, tạo đối tượng Article với ID
        if (articlePartDTO.getArticleId() != null) {
            Article article = articleRepository.findById(articlePartDTO.getArticleId()).orElse(null);
            articlePart.setArticle(article);
        }

        return articlePart;
    }
}
