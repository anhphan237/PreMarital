package com.example.premarital.bankAccount.repository;

import com.example.premarital.bankAccount.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
