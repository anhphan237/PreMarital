package com.example.premarital.controllers;

import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.UserQuizHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userQuizHistories")
public class UserQuizHistoryController {
    private final UserQuizHistoryService userQuizHistoryService;

    public UserQuizHistoryController(UserQuizHistoryService userQuizHistoryService) {
        this.userQuizHistoryService = userQuizHistoryService;
    }

    @GetMapping
    public ResponseEntity<Page<UserQuizHistoryDTO>> getWallets(
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
        Page<UserQuizHistoryDTO> userQuizHistories = userQuizHistoryService.getUserQuizHistories(pageable);
        return new ResponseEntity<>(userQuizHistories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUserQuizHistory(@RequestBody UserQuizHistoryDTO userQuizHistoryDTO){
        userQuizHistoryService.createUserQuizHistory(userQuizHistoryDTO);
        return new ResponseEntity<>("User's Quiz History created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQuizHistoryDTO> findUserQuizHistoryById(@PathVariable Long id){
        UserQuizHistoryDTO userQuizHistory = userQuizHistoryService.getUserQuizHistoryById(id);
        return new ResponseEntity<>(userQuizHistory, userQuizHistory != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserQuizHistory(@PathVariable Long id) {
        boolean deleted = userQuizHistoryService.deleteUserQuizHistoryById(id);
        return deleted
                ? ResponseEntity.ok("User Quiz History deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Quiz History not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserQuizHistory(@PathVariable Long id, @RequestBody UserQuizHistoryDTO userQuizHistoryDTO) {
        boolean updated = userQuizHistoryService.updateUserQuizHistory(id, userQuizHistoryDTO);
        return updated
                ? ResponseEntity.ok("User Quiz History updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Quiz History not found");
    }
}
