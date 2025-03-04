package com.example.premarital.controllers;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.services.QuestionOptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionOptions")
public class QuestionOptionController {
    private final QuestionOptionService questionOptionService;

    public QuestionOptionController(QuestionOptionService questionOptionService) {
        this.questionOptionService = questionOptionService;
    }

    @GetMapping
    public ResponseEntity<Page<QuestionOptionDTO>> findAll(
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
        Page<QuestionOptionDTO> questionOptions = questionOptionService.getQuestionOptions(pageable);
        return new ResponseEntity<>(questionOptions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuestionOption(@RequestBody QuestionOptionDTO questionOptionDTO){
        questionOptionService.createQuestionOption(questionOptionDTO);
        return new ResponseEntity<>("Question option created successfully",HttpStatus.CREATED);
    }
}
