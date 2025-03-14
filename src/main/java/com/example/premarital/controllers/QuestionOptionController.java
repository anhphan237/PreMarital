package com.example.premarital.controllers;

import com.example.premarital.dtos.ArticlePartDTO;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.dtos.WithdrawRequestDTO;
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
        Page<QuestionOptionDTO> questionOptions = questionOptionService.getQuestionOptions(pageable);
        return new ResponseEntity<>(questionOptions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuestionOption(@RequestBody QuestionOptionDTO questionOptionDTO){
        questionOptionService.createQuestionOption(questionOptionDTO);
        return new ResponseEntity<>("Question option created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionOptionDTO> findQuestionOptionById(@PathVariable Long id){
        QuestionOptionDTO questionOption = questionOptionService.getQuestionOptionById(id);
        return new ResponseEntity<>(questionOption, questionOption != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestionOption(@PathVariable Long id) {
        boolean deleted = questionOptionService.deleteQuestionOptionById(id);
        return deleted
                ? ResponseEntity.ok("Question Option deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question Option not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuestionOption(@PathVariable Long id, @RequestBody QuestionOptionDTO questionOptionDTO) {
        boolean updated = questionOptionService.updateQuestionOption(id, questionOptionDTO);
        return updated
                ? ResponseEntity.ok("Question Option updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question Option not found");
    }
}
