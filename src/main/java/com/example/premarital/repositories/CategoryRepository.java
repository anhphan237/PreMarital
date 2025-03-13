package com.example.premarital.repositories;

import com.example.premarital.models.Category;
import com.example.premarital.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findCategoriesByIsActiveTrue(Pageable pageable);
}
