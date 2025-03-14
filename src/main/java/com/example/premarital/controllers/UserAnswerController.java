package com.example.premarital.controllers;

import com.example.premarital.dtos.UserAnswerDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.UserAnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAnswers")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @GetMapping
    public ResponseEntity<?> getUserAnswers(
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
        Page<UserAnswerDTO> userAnswerDTOS = userAnswerService.getUserAnswers(pageable);
        return new ResponseEntity<>(userAnswerDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUserAnswer(@RequestBody UserAnswerDTO userAnswerDTO){
        userAnswerService.createUserAnswer(userAnswerDTO);
        return new ResponseEntity<>("User Answer created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAnswerDTO> findUserAnswerById(@PathVariable Long id){
        UserAnswerDTO userAnswer = userAnswerService.getUserAnswerById(id);
        return new ResponseEntity<>(userAnswer, userAnswer != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserAnswer(@PathVariable Long id) {
        boolean deleted = userAnswerService.deleteUserAnswerById(id);
        return deleted
                ? ResponseEntity.ok("User's Answer deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User's Answer not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserAnswer(@PathVariable Long id, @RequestBody UserAnswerDTO updatedUserAnswer) {
        boolean updated = userAnswerService.updateUserAnswer(id, updatedUserAnswer);
        return updated
                ? ResponseEntity.ok("User's Answer updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User's Answer not found");
    }
}
