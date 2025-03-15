package com.example.premarital.repositories;

import com.example.premarital.models.BankAccount;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Page<BankAccount> findBankAccountsByIsActiveTrue(Pageable pageable);
}
