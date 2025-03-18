package com.example.premarital.services.impl;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.ArticleMapper;
import com.example.premarital.models.Article;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.ArticleRepository;
import com.example.premarital.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public Page<ArticleDTO> getArticles(Pageable pageable) {
        Page<Article> entities = articleRepository.findAll(pageable);
        Page<ArticleDTO> dtoPage = entities.map(new Function<Article, ArticleDTO>() {

            @Override
            public ArticleDTO apply(Article article) {
                ArticleDTO dto = articleMapper.toDTO(article);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createArticle(ArticleDTO dto) {
        Article article = articleMapper.toEntity(dto);
        articleRepository.save(article);
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        return articleMapper.toDTO(articleRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteArticleById(Long id) {
        return articleRepository.findById(id).map(article -> {
            article.setIsActive(false);
            articleRepository.save(article);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateArticle(Long id, ArticleDTO updatedArticleDTO) {
        return articleRepository.findById(id).map(article -> {
            Article updatedArticle = articleMapper.toEntityWithId(id, updatedArticleDTO);
            articleRepository.save(updatedArticle);
            return true;
        }).orElse(false);
    }
}
