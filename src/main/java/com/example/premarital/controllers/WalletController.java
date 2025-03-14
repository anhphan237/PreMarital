package com.example.premarital.controllers;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.dtos.UserDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.UserService;
import com.example.premarital.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getWallets(
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
        Page<WalletDTO> wallets = walletService.getWallets(pageable);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createWallet(@RequestBody WalletDTO wallet){
        walletService.createWallet(wallet);
        return new ResponseEntity<>("Wallet created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> findWalletById(@PathVariable Long id){
        WalletDTO wallet = walletService.getWalletById(id);
        return new ResponseEntity<>(wallet, wallet != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable Long id) {
        boolean deleted = walletService.deleteWalletById(id);
        return deleted
                ? ResponseEntity.ok("Wallet deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWallet(@PathVariable Long id, @RequestBody WalletDTO updatedWallet) {
        boolean updated = walletService.updateWallet(id, updatedWallet);
        return updated
                ? ResponseEntity.ok("Wallet updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
    }
}
