package com.example.premarital.repositories;

import com.example.premarital.models.ArticlePart;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlePartRepository extends JpaRepository<ArticlePart, Long> {
    Page<ArticlePart> findArticlePartsByIsActiveTrue(Pageable pageable);
}
