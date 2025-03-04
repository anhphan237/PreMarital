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
    public ResponseEntity<Page<QuizUserAdviceDTO>> getQuizUserAdviceDTOs(
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
        Page<QuizUserAdviceDTO> quizUserAdviceDTOS = quizUserAdviceService.getQuizUserAdvices(pageable);
        return new ResponseEntity<>(quizUserAdviceDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuizUserAdvice(@RequestBody QuizUserAdviceDTO quizUserAdviceDTO){
        quizUserAdviceService.createQuizUserAdvice(quizUserAdviceDTO);
        return new ResponseEntity<>("Quiz's Advice created successfully",HttpStatus.CREATED);
    }
}
