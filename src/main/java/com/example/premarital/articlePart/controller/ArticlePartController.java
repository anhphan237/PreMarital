package com.example.premarital.articlePart.controller;

import com.example.premarital.articlePart.service.ArticlePartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleParts")
public class ArticlePartController {
    private final ArticlePartService articlePartService;

    public ArticlePartController(ArticlePartService articlePartService) {
        this.articlePartService = articlePartService;
    }
}
