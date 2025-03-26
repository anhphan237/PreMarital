package com.example.premarital.services.impl;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.ArticlePartMapper;
import com.example.premarital.models.ArticlePart;
import com.example.premarital.repositories.ArticlePartRepository;
import com.example.premarital.repositories.ArticleRepository;
import com.example.premarital.services.ArticlePartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticlePartServiceImpl implements ArticlePartService {
    private final ArticlePartRepository articlePartRepository;
    private final ArticlePartMapper articlePartMapper;
    private static final Logger logger = LoggerFactory.getLogger(ArticlePartServiceImpl.class);
    private final ArticleRepository articleRepository;

    @Override
    public Page<ArticlePartDTO> getArticleParts(Pageable pageable) {
        Page<ArticlePart> articleParts = articlePartRepository.findArticlePartsByIsActiveTrue(pageable);
        if (articleParts.isEmpty()) {
            logger.warn("No article parts actively found in the system");
        }
        return articleParts.map(articlePartMapper::toDTO);
    }

    @Override
    @Transactional
    public void createArticlePart(ArticlePartDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when create");
        }

        if (!articleRepository.existsById(dto.getArticleId())) {
            throw new EntityNotFoundException("Article ID " + dto.getArticleId() + " not found");
        }

        try {
            ArticlePart articlePart = articlePartMapper.toEntity(dto);
            articlePartRepository.save(articlePart);
            logger.info("Article part created successfully with ID: {}", articlePart.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating article part: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid article part data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating article part: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create article part", e);
        }
    }

    @Override
    public ArticlePartDTO getArticlePartById(Long id) {
        return articlePartRepository.findById(id)
                .map(articlePartMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("ArticlePart with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteArticlePartById(Long id) {
        ArticlePart articlePart = articlePartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));

        if (!articlePart.getIsActive()) {
            logger.warn("Article Part with ID {} is already inactive", id);
            return false;
        }

        try {
            articlePart.setIsActive(false);
            articlePartRepository.save(articlePart);
            logger.info("Article Part with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating articlePart with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate articlePart", e);
        }
    }

    @Override
    @Transactional
    public boolean updateArticlePart(Long id, ArticlePartDTO updatedArticlePartDTO) {
        if (!articlePartRepository.existsById(id)) {
            throw new EntityNotFoundException("Article part ID " + id + " not found");
        }

        try {
            ArticlePart updatedArticle = articlePartMapper.toEntityWithId(id, updatedArticlePartDTO);
            articlePartRepository.save(updatedArticle);
            logger.info("Article part with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating article part with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating article part with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update article part", e);
        }
    }
}
