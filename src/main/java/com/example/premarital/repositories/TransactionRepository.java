package com.example.premarital.repositories;

import com.example.premarital.models.Transaction;
import com.example.premarital.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findTransactionsByIsActiveTrue(Pageable pageable);
}
