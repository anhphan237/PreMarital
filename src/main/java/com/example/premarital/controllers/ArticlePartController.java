package com.example.premarital.controllers;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.models.ArticlePart;
import com.example.premarital.services.ArticlePartService;
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
@RequestMapping("/articleParts")
public class ArticlePartController {
    private final ArticlePartService articlePartService;

    @GetMapping
    public ResponseEntity<?> findAll(
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
        Page<ArticlePartDTO> articleParts = articlePartService.getArticleParts(pageable);
        return new ResponseEntity<>(articleParts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createArticlePart(@RequestBody ArticlePartDTO articlePart){
        articlePartService.createArticlePart(articlePart);
        return new ResponseEntity<>("Article part created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticlePartDTO> findArticlePartById(@PathVariable Long id){
        ArticlePartDTO articlePart = articlePartService.getArticlePartById(id);
        return new ResponseEntity<>(articlePart, articlePart != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticlePart(@PathVariable Long id) {
        boolean deleted = articlePartService.deleteArticlePartById(id);
        return deleted
                ? ResponseEntity.ok("Article Part deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article Part not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticlePart(@PathVariable Long id, @RequestBody ArticlePartDTO updatedArticlePart) {
        boolean updated = articlePartService.updateArticlePart(id, updatedArticlePart);
        return updated
                ? ResponseEntity.ok("Article Part updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article Part not found");
    }
}
