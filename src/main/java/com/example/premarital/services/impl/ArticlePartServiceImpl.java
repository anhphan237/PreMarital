package com.example.premarital.services.impl;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.mappers.ArticlePartMapper;
import com.example.premarital.models.ArticlePart;
import com.example.premarital.repositories.ArticlePartRepository;
import com.example.premarital.services.ArticlePartService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ArticlePartServiceImpl implements ArticlePartService {
    private final ArticlePartRepository articlePartRepository;
    private final ArticlePartMapper articlePartMapper;

    @Override
    public Page<ArticlePartDTO> getArticleParts(Pageable pageable) {
        Page<ArticlePart> entities = articlePartRepository.findAll(pageable);
        Page<ArticlePartDTO> dtoPage = entities.map(new Function<ArticlePart, ArticlePartDTO>() {

            @Override
            public ArticlePartDTO apply(ArticlePart articlePart) {
                ArticlePartDTO dto = articlePartMapper.toDTO(articlePart);
                return dto;
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
    public ArticlePartDTO getArticlePartById(Long id) {
        return articlePartMapper.toDTO(articlePartRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteArticlePartById(Long id) {
        return articlePartRepository.findById(id).map(articlePart -> {
            articlePart.setIsActive(false);
            articlePartRepository.save(articlePart);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateArticlePart(Long id, ArticlePartDTO updatedArticlePartDTO) {
        return articlePartRepository.findById(id).map(article -> {
            ArticlePart updatedArticle = articlePartMapper.toEntityWithId(id, updatedArticlePartDTO);
            articlePartRepository.save(updatedArticle);
            return true;
        }).orElse(false);
    }
}
