package com.example.premarital.controllers;

import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.services.BankAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<Page<BankAccountDTO>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Sort.Direction direction
    ){
        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<BankAccountDTO> bankAccountDTOs = bankAccountService.getBankAccounts(pageable);
        return new ResponseEntity<>(bankAccountDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody BankAccountDTO bankAccountDTO){
        bankAccountService.createBankAccount(bankAccountDTO);
        return new ResponseEntity<>("Bank Account created successfully",HttpStatus.CREATED);
    }
}
