package com.example.premarital.controllers;

import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<Page<QuizDTO>> getQuizzes(
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
        Page<QuizDTO> quizzes = quizService.getQuizzes(pageable);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        quizService.createQuiz(quizDTO);
        return new ResponseEntity<>("Quiz created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> findQuizById(@PathVariable Long id){
        QuizDTO quiz = quizService.getQuizById(id);
        return new ResponseEntity<>(quiz, quiz != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id) {
        boolean deleted = quizService.deleteQuizById(id);
        return deleted
                ? ResponseEntity.ok("Quiz deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuiz(@PathVariable Long id, @RequestBody QuizDTO updatedQuiz) {
        boolean updated = quizService.updateQuiz(id, updatedQuiz);
        return updated
                ? ResponseEntity.ok("Quiz updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
    }
}
