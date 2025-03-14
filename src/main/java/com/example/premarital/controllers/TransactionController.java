package com.example.premarital.controllers;

import com.example.premarital.dtos.TransactionDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.models.Transaction;
import com.example.premarital.services.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<?> getTransactions(
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

        Page<TransactionDTO> transactions = transactionService.getTransactions(pageable);
        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>("Transaction created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findTransactionById(@PathVariable Long id){
        TransactionDTO transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction, transaction != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        boolean deleted = transactionService.deleteTransactionById(id);
        return deleted
                ? ResponseEntity.ok("Transaction deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO updatedTransaction) {
        boolean updated = transactionService.updateTransaction(id, updatedTransaction);
        return updated
                ? ResponseEntity.ok("Transaction updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
    }
}
