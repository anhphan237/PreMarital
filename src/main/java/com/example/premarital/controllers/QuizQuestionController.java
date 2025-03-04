package com.example.premarital.controllers;

import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.services.QuizQuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizQuestions")
public class QuizQuestionController {
    private final QuizQuestionService quizQuestionService;

    public QuizQuestionController(QuizQuestionService quizQuestionService) {
        this.quizQuestionService = quizQuestionService;
    }

    @GetMapping
    public ResponseEntity<Page<QuizQuestionDTO>> getQuizQuestions(
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
        Page<QuizQuestionDTO> quizQuestions = quizQuestionService.getQuizQuestions(pageable);
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuizQuestion(@RequestBody QuizQuestionDTO quizQuestionDTO){
        quizQuestionService.createQuizQuestion(quizQuestionDTO);
        return new ResponseEntity<>("Quiz Question created successfully",HttpStatus.CREATED);
    }
}
