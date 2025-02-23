package com.example.premarital.transaction.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.transaction.dto.TransactionDTO;
import com.example.premarital.transaction.model.Transaction;
import com.example.premarital.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public PagingResult<TransactionDTO> getTransactions(PaginationRequest request) {
        return null;
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO dto) {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

    @Override
    public boolean deleteTransactionById(Long id) {
        return false;
    }

    @Override
    public boolean updateTransaction(Long id, TransactionDTO updatedTransactionDTO) {
        return false;
    }
}
