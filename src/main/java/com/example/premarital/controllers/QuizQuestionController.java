package com.example.premarital.controllers;

import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.QuizQuestionService;
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
@RequestMapping("/quizQuestions")
public class QuizQuestionController {
    private final QuizQuestionService quizQuestionService;

    @GetMapping
    public ResponseEntity<?> getQuizQuestions(
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
        Page<QuizQuestionDTO> quizQuestions = quizQuestionService.getQuizQuestions(pageable);
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuizQuestion(@RequestBody QuizQuestionDTO quizQuestionDTO){
        quizQuestionService.createQuizQuestion(quizQuestionDTO);
        return new ResponseEntity<>("Quiz Question created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizQuestionDTO> findQuizQuestionById(@PathVariable Long id){
        QuizQuestionDTO quizQuestion = quizQuestionService.getQuizQuestionById(id);
        return new ResponseEntity<>(quizQuestion, quizQuestion != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuizQuestion(@PathVariable Long id) {
        boolean deleted = quizQuestionService.deleteQuizQuestionById(id);
        return deleted
                ? ResponseEntity.ok("Quiz Question deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Question not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuizQuestion(@PathVariable Long id, @RequestBody QuizQuestionDTO quizQuestionDTO) {
        boolean updated = quizQuestionService.updateQuizQuestion(id, quizQuestionDTO);
        return updated
                ? ResponseEntity.ok("Quiz Question updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Question not found");
    }
}
