package com.example.premarital.repositories;

import com.example.premarital.models.Wallet;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Page<Wallet> findWalletsByIsActiveTrue(Pageable pageable);
}
