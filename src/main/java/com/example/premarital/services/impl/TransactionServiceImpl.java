package com.example.premarital.services.impl;

import com.example.premarital.dtos.TransactionDTO;
import com.example.premarital.mappers.TransactionMapper;
import com.example.premarital.models.Transaction;
import com.example.premarital.repositories.TransactionRepository;
import com.example.premarital.services.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Page<TransactionDTO> getTransactions(Pageable pageable) {
        Page<Transaction> entities = transactionRepository.findAll(pageable);
        Page<TransactionDTO> dtoPage = entities.map(new Function<Transaction, TransactionDTO>() {

            @Override
            public TransactionDTO apply(Transaction transaction) {
                TransactionDTO dto = transactionMapper.toDTO(transaction);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createTransaction(TransactionDTO dto) {
        Transaction transaction = transactionMapper.toEntity(dto);
        transactionRepository.save(transaction);
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
