package com.example.premarital.repositories;

import com.example.premarital.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findTransactionsByIsActiveTrue(Pageable pageable);
    Page<Transaction> findTransactionsByWalletId(Pageable pageable, Long walletId);
}
