package com.example.premarital.services.impl;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.ArticleMapper;
import com.example.premarital.models.Article;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.ArticleRepository;
import com.example.premarital.repositories.CategoryRepository;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.ArticleService;
import com.example.premarital.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ArticleDTO> getArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findArticlesByIsActiveTrue(pageable);
        if (articles.isEmpty()) {
            logger.warn("No articles found in the system");
        }
        return articles.map(articleMapper::toDTO);
    }

    @Override
    @Transactional
    public void createArticle(ArticleDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when creating an article");
        }

        if (dto.getApprovedUserId() == null || dto.getApprovedUserId() <= 0 || !userRepository.existsById(dto.getApprovedUserId())) {
            throw new InvalidDataException("Approved user ID " + dto.getApprovedUserId() + " is invalid or not found");
        }

        if (dto.getReferenceArticleId() == null || dto.getReferenceArticleId() <= 0 || !articleRepository.existsById(dto.getReferenceArticleId())) {
            throw new InvalidDataException("Reference article ID " + dto.getReferenceArticleId() + " is invalid or not found");
        }

        if (dto.getCategoryId() == null || dto.getCategoryId() <= 0 || !categoryRepository.existsById(dto.getCategoryId())) {
            throw new InvalidDataException("Category ID " + dto.getCategoryId() + " is invalid or not found");
        }

        try {
            Article article = articleMapper.toEntity(dto);
            articleRepository.save(article);
            logger.info("Article created successfully with ID: {}", article.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating article: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid article data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating article: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create article", e);
        }
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Article with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article with ID " + id + " not found"));

        if (!article.getIsActive()) {
            logger.warn("Article with ID {} is already inactive", id);
            return false;
        }

        try {
            article.setIsActive(false);
            articleRepository.save(article);
            logger.info("Article with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating article with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate article", e);
        }
    }

    @Override
    @Transactional
    public boolean updateArticle(Long id, ArticleDTO updatedArticleDTO) {
        if (updatedArticleDTO.getId() != null) {
            throw new InvalidDataException("ID must be null when updating an article");
        }

        if (!articleRepository.existsById(id)) {
            throw new EntityNotFoundException("Article ID " + id + " not found");
        }

        if (updatedArticleDTO.getApprovedUserId() == null || updatedArticleDTO.getApprovedUserId() <= 0 || !userRepository.existsById(updatedArticleDTO.getApprovedUserId())) {
            throw new InvalidDataException("Approved user ID " + updatedArticleDTO.getApprovedUserId() + " is invalid or not found");
        }

        if (updatedArticleDTO.getReferenceArticleId() == null || updatedArticleDTO.getReferenceArticleId() <= 0 || !articleRepository.existsById(updatedArticleDTO.getReferenceArticleId())) {
            throw new InvalidDataException("Reference article ID " + updatedArticleDTO.getReferenceArticleId() + " is invalid or not found");
        }

        if (updatedArticleDTO.getCategoryId() == null || updatedArticleDTO.getCategoryId() <= 0 || !categoryRepository.existsById(updatedArticleDTO.getCategoryId())) {
            throw new InvalidDataException("Category ID " + updatedArticleDTO.getCategoryId() + " is invalid or not found");
        }

        try {
            Article updatedArticle = articleMapper.toEntityWithId(id, updatedArticleDTO);
            articleRepository.save(updatedArticle);
            logger.info("Article with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating article with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating article with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update article", e);
        }
    }
}
