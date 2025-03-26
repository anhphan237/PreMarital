package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.mappers.ArticleMapper;
import com.example.premarital.models.Article;
import com.example.premarital.models.Category;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.User;
import com.example.premarital.repositories.ArticleRepository;
import com.example.premarital.repositories.CategoryRepository;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapperImpl implements ArticleMapper {
    private final UserRepository userRepository;
    private final TherapistRepository therapistRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    public ArticleMapperImpl(UserRepository userRepository, TherapistRepository therapistRepository, CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.therapistRepository = therapistRepository;
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDTO toDTO(Article article) {
        if (article == null) {
            return null;
        }

        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getReferencePath(),
                article.getStatus(),
                (article.getApprovedAdmin() != null) ? article.getApprovedAdmin().getId() : null,
                (article.getTherapist() != null) ? article.getTherapist().getId() : null,
                (article.getCategory() != null) ? article.getCategory().getId() : null,
                (article.getReferenceArticle() != null) ? article.getReferenceArticle().getId() : null,
                article.getIsActive()
        );
    }

    @Override
    public Article toEntity(ArticleDTO dto) {
        if (dto == null) {
            return null;
        }

        Article ref = new Article();
        ref.setId(dto.getReferenceArticleId());
        Category category = new Category();
        category.setId(dto.getCategoryId());
        Therapist therapist = new Therapist();
        therapist.setId(dto.getTherapistId());
        User approvedAdmin = new User();
        approvedAdmin.setId(dto.getApprovedUserId());

        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setReferencePath(dto.getReferencePath());
        article.setStatus(dto.getStatus());
        article.setIsActive(dto.getIsActive());
        article.setReferenceArticle(ref);
        article.setCategory(category);
        article.setApprovedAdmin(approvedAdmin);
        article.setTherapist(therapist);

        return getArticle(dto, article);
    }

    private Article getArticle(ArticleDTO dto, Article article) {
        if (dto.getApprovedUserId() != null) {
            User user = userRepository.findById(dto.getApprovedUserId()).orElse(null);
            article.setApprovedAdmin(user);
        }

        if (dto.getTherapistId() != null) {
            Therapist therapist = therapistRepository.findById(dto.getTherapistId()).orElse(null);
            article.setTherapist(therapist);
        }

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.getReferenceById(dto.getCategoryId());
            article.setCategory(category);
        }

        return article;
    }

    @Override
    public Article toEntityWithId(Long id, ArticleDTO dto) {
        if (dto == null) {
            return null;
        }

        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setReferencePath(dto.getReferencePath());
        article.setStatus(dto.getStatus());
        article.setIsActive(dto.getIsActive());

        return getArticle(dto, article);
    }
}
