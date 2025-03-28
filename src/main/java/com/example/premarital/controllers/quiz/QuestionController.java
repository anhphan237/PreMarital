package com.example.premarital.controllers.quiz;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.dtos.QuestionListItemDTO;
import com.example.premarital.models.Question;
import com.example.premarital.models.Therapist;
import com.example.premarital.services.JwtService;
import com.example.premarital.services.QuestionService;
import com.example.premarital.services.TherapistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@Tag(name = "Questions", description = "API for managing questions")
public class QuestionController {

    private final QuestionService questionService;
    private final JwtService jwtService;
    private final TherapistService therapistService;

    @GetMapping("/{id}")
    @Operation(summary = "Get question by ID")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @GetMapping
    @Operation(summary = "Get all questions or filter by criteria")
    public ResponseEntity<List<QuestionListItemDTO>> getQuestions(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Question.QuestionGender gender
    ) {
        if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(questionService.searchQuestions(search));
        } else if (gender != null) {
            return ResponseEntity.ok(questionService.getQuestionsByGender(gender));
        } else {
            return ResponseEntity.ok(questionService.getAllQuestions());
        }
    }

    @GetMapping("/my-questions")
    @Operation(summary = "Get questions used by the authenticated therapist")
    public ResponseEntity<List<QuestionListItemDTO>> getMyQuestions(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getUserEmail(token);
        Therapist therapist = therapistService.getTherapistByEmail(email);
        
        return ResponseEntity.ok(questionService.getQuestionsByTherapistId(therapist.getId()));
    }
} 