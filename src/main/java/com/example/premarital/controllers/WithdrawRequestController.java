package com.example.premarital.controllers;

import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.services.WithdrawRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/withdrawRequests")
public class WithdrawRequestController {
    private final WithdrawRequestService withdrawRequestService;

    public WithdrawRequestController(WithdrawRequestService withdrawRequestService) {
        this.withdrawRequestService = withdrawRequestService;
    }

    @GetMapping
    public ResponseEntity<Page<WithdrawRequestDTO>> getUsers(
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
        Page<WithdrawRequestDTO> withdrawRequests = withdrawRequestService.getWithdrawRequests(pageable);
        return new ResponseEntity<>(withdrawRequests, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createWithdrawRequest(@RequestBody WithdrawRequestDTO withdrawRequestDTO){
        withdrawRequestService.createWithdrawRequest(withdrawRequestDTO);
        return new ResponseEntity<>("Withdraw Request created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WithdrawRequestDTO> findWithdrawRequestById(@PathVariable Long id){
        WithdrawRequestDTO withdrawRequest = withdrawRequestService.getWithdrawRequestById(id);
        return new ResponseEntity<>(withdrawRequest, withdrawRequest != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWithdrawRequest(@PathVariable Long id) {
        boolean deleted = withdrawRequestService.deleteWithdrawRequestById(id);
        return deleted
                ? ResponseEntity.ok("Withdraw Request deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Withdraw Request not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWithdrawRequest(@PathVariable Long id, @RequestBody WithdrawRequestDTO updatedWithdrawRequest) {
        boolean updated = withdrawRequestService.updateWithdrawRequest(id, updatedWithdrawRequest);
        return updated
                ? ResponseEntity.ok("Withdraw Request updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Withdraw Request not found");
    }
}
