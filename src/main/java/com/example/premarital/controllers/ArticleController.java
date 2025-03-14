package com.example.premarital.controllers;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<?> getArticles(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        if (page < 1 || size <= 1) {
            return ResponseEntity.badRequest().body("Page number must be >= 1 and size must be > 1");
        }

        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<ArticleDTO> articles = articleService.getArticles(pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody ArticleDTO article){
        articleService.createArticle(article);
        return new ResponseEntity<>("Article created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> findArticleById(@PathVariable Long id){
        ArticleDTO article = articleService.getArticleById(id);
        return new ResponseEntity<>(article, article != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        boolean deleted = articleService.deleteArticleById(id);
        return deleted
                ? ResponseEntity.ok("Article deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO updatedArticle) {
        boolean updated = articleService.updateArticle(id, updatedArticle);
        return updated
                ? ResponseEntity.ok("Article updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
    }
}
