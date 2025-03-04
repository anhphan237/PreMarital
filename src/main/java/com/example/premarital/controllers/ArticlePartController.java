package com.example.premarital.controllers;

import com.example.premarital.dtos.ArticleDTO;
import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.services.ArticlePartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleParts")
public class ArticlePartController {
    private final ArticlePartService articlePartService;

    public ArticlePartController(ArticlePartService articlePartService) {
        this.articlePartService = articlePartService;
    }

    @GetMapping
    public ResponseEntity<Page<ArticlePartDTO>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Sort.Direction direction
    ) {
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
}
