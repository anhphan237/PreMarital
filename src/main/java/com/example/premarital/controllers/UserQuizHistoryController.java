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
        Page<UserQuizHistoryDTO> userQuizHistorys = userQuizHistoryService.getUserQuizHistorys(pageable);
        return new ResponseEntity<>(userQuizHistorys, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUserQuizHistory(@RequestBody UserQuizHistoryDTO userQuizHistoryDTO){
        userQuizHistoryService.createUserQuizHistory(userQuizHistoryDTO);
        return new ResponseEntity<>("User's Quiz History created successfully",HttpStatus.CREATED);
    }
}
