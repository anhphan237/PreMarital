package com.example.premarital.transaction.service;

import com.example.premarital.bankAccount.dto.BankAccountDTO;
import com.example.premarital.bankAccount.model.BankAccount;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.transaction.dto.TransactionDTO;
import com.example.premarital.transaction.model.Transaction;

public interface TransactionService {
    PagingResult<TransactionDTO> getTransactions(PaginationRequest request);
    TransactionDTO createTransaction(TransactionDTO dto);
    Transaction getTransactionById(Long id);
    boolean deleteTransactionById(Long id);
    boolean updateTransaction(Long id, TransactionDTO updatedTransactionDTO);
}
