package com.example.premarital.controllers;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.QuestionService;
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
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

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
        Page<QuestionDTO> questions = questionService.getQuestions(pageable);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionDTO questionDTO){
        questionService.createQuestion(questionDTO);
        return new ResponseEntity<>("Question created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> findQuestionById(@PathVariable Long id){
        QuestionDTO question = questionService.getQuestionById(id);
        return new ResponseEntity<>(question, question != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        boolean deleted = questionService.deleteQuestionById(id);
        return deleted
                ? ResponseEntity.ok("Question deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO updatedQuestion) {
        boolean updated = questionService.updateQuestion(id, updatedQuestion);
        return updated
                ? ResponseEntity.ok("Question updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
    }
}
