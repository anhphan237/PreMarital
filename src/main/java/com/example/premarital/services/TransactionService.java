package com.example.premarital.services;

import com.example.premarital.dtos.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    Page<TransactionDTO> getTransactions(Pageable pageable);
    void createTransaction(TransactionDTO dto);
    TransactionDTO getTransactionById(Long id);
    Page<TransactionDTO> getTransactionsByWalletId(Pageable pageable, Long walletId);
    boolean deleteTransactionById(Long id);
    boolean updateTransaction(Long id, TransactionDTO updatedTransactionDTO);
}
