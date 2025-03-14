package com.example.premarital.controllers;

import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.QuizUserAdviceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizUserAdvices")
public class QuizUserAdviceController {
    private final QuizUserAdviceService quizUserAdviceService;

    public QuizUserAdviceController(QuizUserAdviceService quizUserAdviceService) {
        this.quizUserAdviceService = quizUserAdviceService;
    }

    @GetMapping
    public ResponseEntity<?> getQuizUserAdviceDTOs(
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
        Page<QuizUserAdviceDTO> quizUserAdviceDTOS = quizUserAdviceService.getQuizUserAdvices(pageable);
        return new ResponseEntity<>(quizUserAdviceDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuizUserAdvice(@RequestBody QuizUserAdviceDTO quizUserAdviceDTO){
        quizUserAdviceService.createQuizUserAdvice(quizUserAdviceDTO);
        return new ResponseEntity<>("Quiz's Advice created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizUserAdviceDTO> findQuizUserAdviceById(@PathVariable Long id){
        QuizUserAdviceDTO quizUserAdvice = quizUserAdviceService.getQuizUserAdviceById(id);
        return new ResponseEntity<>(quizUserAdvice, quizUserAdvice != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuizUserAdvice(@PathVariable Long id) {
        boolean deleted = quizUserAdviceService.deleteQuizUserAdviceById(id);
        return deleted
                ? ResponseEntity.ok("Quiz's Advice deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz's Advice not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuizUserAdvice(@PathVariable Long id, @RequestBody QuizUserAdviceDTO quizUserAdviceDTO) {
        boolean updated = quizUserAdviceService.updateQuizUserAdvice(id, quizUserAdviceDTO);
        return updated
                ? ResponseEntity.ok("Quiz's Advice updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz's Advice not found");
    }
}
