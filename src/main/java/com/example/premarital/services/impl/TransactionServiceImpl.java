package com.example.premarital.services.impl;

import com.example.premarital.dtos.TransactionDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.TransactionMapper;
import com.example.premarital.models.Transaction;
import com.example.premarital.repositories.TransactionRepository;
import com.example.premarital.repositories.WalletRepository;
import com.example.premarital.services.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final TransactionMapper transactionMapper;
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Override
    public Page<TransactionDTO> getTransactions(Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findTransactionsByIsActiveTrue(pageable);
        if (transactions.isEmpty()) {
            logger.warn("No schedules actively found in the system");
        }
        return transactions.map(transactionMapper::toDTO);
    }

    @Override
    @Transactional
    public void createTransaction(TransactionDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when create");
        }

        if (dto.getWalletId() == null || !walletRepository.existsById(dto.getWalletId())) {
            throw new InvalidDataException("Invalid wallet ID: " + dto.getWalletId());
        }

        try {
            Transaction transaction = transactionMapper.toEntity(dto);
            transaction.setIsActive(true);
            transactionRepository.save(transaction);
            logger.info("Transaction created successfully with ID: {}", transaction.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating transaction: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid transaction data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating transaction: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create transaction", e);
        }
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with ID " + id + " not found"));

        if (!transaction.getIsActive()) {
            logger.warn("Transaction with ID {} is already inactive", id);
            return false;
        }

        try {
            transaction.setIsActive(false);
            transactionRepository.save(transaction);
            logger.info("Transaction with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating transaction with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate transaction", e);
        }
    }

    @Override
    @Transactional
    public boolean updateTransaction(Long id, TransactionDTO updatedTransactionDTO) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with ID " + id + " not found"));

        if (!existingTransaction.getWallet().getId().equals(updatedTransactionDTO.getWalletId()) &&
                (updatedTransactionDTO.getWalletId() == null || !walletRepository.existsById(updatedTransactionDTO.getWalletId()))) {
            throw new InvalidDataException("Invalid wallet ID: " + updatedTransactionDTO.getWalletId());
        }

        try {
            Transaction updatedTransaction = transactionMapper.toEntityWithId(id, updatedTransactionDTO);
            transactionRepository.save(updatedTransaction);
            logger.info("Transaction with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating transaction with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating transaction with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update transaction", e);
        }
    }
}
