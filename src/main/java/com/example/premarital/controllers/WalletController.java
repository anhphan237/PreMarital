package com.example.premarital.controllers;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.WalletService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<Page<WalletDTO>> getWallets(
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
        Page<WalletDTO> wallets = walletService.getWallets(pageable);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createWallet(@RequestBody WalletDTO wallet){
        walletService.createWallet(wallet);
        return new ResponseEntity<>("Wallet created successfully",HttpStatus.CREATED);
    }
}
