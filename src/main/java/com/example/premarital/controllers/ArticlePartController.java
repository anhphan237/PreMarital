package com.example.premarital.controllers;

import com.example.premarital.services.ArticlePartService;
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
