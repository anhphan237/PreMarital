package com.example.premarital.controllers.quiz;

import com.example.premarital.dtos.QuizCreationDTO;
import com.example.premarital.models.Therapist;
import com.example.premarital.services.JwtService;
import com.example.premarital.services.QuizService;
import com.example.premarital.services.TherapistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz-configurator/v1")
@RequiredArgsConstructor
public class QuizConfiguratorController {

    private final TherapistService therapistService;
    private final QuizService quizService;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<Object> createQuiz(
            @RequestBody QuizCreationDTO dto,
            HttpServletRequest httpServletRequest
    ) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);

        String email = jwtService.getUserEmail(token);

        Therapist therapist = therapistService.getTherapistByEmail(email);

        return ResponseEntity.ok(quizService.createQuiz(therapist, dto));
    }
}
