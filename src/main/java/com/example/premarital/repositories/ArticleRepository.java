package com.example.premarital.repositories;

import com.example.premarital.models.Article;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findArticlesByIsActiveTrue(Pageable pageable);
}
