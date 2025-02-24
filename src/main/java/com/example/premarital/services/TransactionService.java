package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.TransactionDTO;
import com.example.premarital.models.Transaction;

public interface TransactionService {
    PagingResult<TransactionDTO> getTransactions(PaginationRequest request);
    TransactionDTO createTransaction(TransactionDTO dto);
    Transaction getTransactionById(Long id);
    boolean deleteTransactionById(Long id);
    boolean updateTransaction(Long id, TransactionDTO updatedTransactionDTO);
}
