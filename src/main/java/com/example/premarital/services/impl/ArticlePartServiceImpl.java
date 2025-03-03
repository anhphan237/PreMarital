package com.example.premarital.services.impl;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.mappers.ArticlePartMapper;
import com.example.premarital.models.Article;
import com.example.premarital.models.ArticlePart;
import com.example.premarital.repositories.ArticlePartRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.services.ArticlePartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ArticlePartServiceImpl implements ArticlePartService {
    private final ArticlePartRepository articlePartRepository;
    private final ArticlePartMapper articlePartMapper;

    public ArticlePartServiceImpl(ArticlePartRepository articlePartRepository, ArticlePartMapper articlePartMapper) {
        this.articlePartRepository = articlePartRepository;
        this.articlePartMapper = articlePartMapper;
    }

    @Override
    public Page<ArticlePartDTO> getArticleParts(Pageable pageable) {
        Page<ArticlePart> entities = articlePartRepository.findAll(pageable);
        Page<ArticlePartDTO> dtoPage = entities.map(new Function<ArticlePart, ArticlePartDTO>() {

            @Override
            public ArticlePartDTO apply(ArticlePart articlePart) {
                return null;
            }
        });
        return dtoPage;
    }

    @Override
    public void createArticlePart(ArticlePartDTO dto) {
        ArticlePart articlePart = articlePartMapper.toEntity(dto);
        articlePartRepository.save(articlePart);
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
