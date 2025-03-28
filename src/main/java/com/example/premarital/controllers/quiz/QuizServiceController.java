package com.example.premarital.controllers.quiz;

import com.example.premarital.dtos.QuizSubmissionDTO;
import com.example.premarital.services.JwtService;
import com.example.premarital.services.QuizService;
import com.example.premarital.services.UserQuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/quiz-svc/v1")
@RequiredArgsConstructor
@Tag(name = "Quiz", description = "Quiz for user Endpoints")
public class QuizServiceController {

    private final QuizService quizService;
    private final UserQuizService userQuizService;
    private final JwtService jwtService;

    @PostMapping("/submit")
    public ResponseEntity<Object> submitQuiz(
            @RequestBody QuizSubmissionDTO quizSubmissionDTO,
            HttpServletRequest httpServletRequest
    ) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);

        String email = jwtService.getUserEmail(token);

        return ResponseEntity.ok(userQuizService.submit(email, quizSubmissionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuizById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/histories")
    public ResponseEntity<Object> getQuizHistories(
            HttpServletRequest httpServletRequest
    ) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);

        String email = jwtService.getUserEmail(token);

        return ResponseEntity.ok(userQuizService.histories(email));
    }
}
