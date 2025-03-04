package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.TransactionDTO;
import com.example.premarital.mappers.TransactionMapper;
import com.example.premarital.models.Transaction;
import com.example.premarital.repositories.WalletRepository;
import com.example.premarital.repositories.WithdrawRequestRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements TransactionMapper {
    private final WalletRepository walletRepository;
    private final WithdrawRequestRepository withdrawRequestRepository;

    public TransactionMapperImpl(WalletRepository walletRepository, WithdrawRequestRepository withdrawRequestRepository) {
        this.walletRepository = walletRepository;
        this.withdrawRequestRepository = withdrawRequestRepository;
    }

    @Override
    public TransactionDTO toDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        return new TransactionDTO(
                transaction.getId(),
                transaction.getWallet() != null ? transaction.getWallet().getId() : null,
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTransactionTime(),
                transaction.getTransactionStatus(),
                transaction.getBalanceBefore(),
                transaction.getTransactionFee(),
                transaction.getTotalAmount(),
                transaction.getWithdrawRequest() != null ? transaction.getWithdrawRequest().getId() : null
        );
    }

    @Override
    public Transaction toEntity(TransactionDTO dto) {
        if (dto == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());

        // Wallet handling if needed
        if (dto.getWalletId() != null) {
            transaction.setWallet(walletRepository.getReferenceById(dto.getWalletId()));
        }
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setTransactionTime(dto.getTransactionTime());
        transaction.setTransactionStatus(dto.getTransactionStatus());
        transaction.setBalanceBefore(dto.getBalanceBefore());
        transaction.setTransactionFee(dto.getTransactionFee());
        transaction.setTotalAmount(dto.getTotalAmount());
        // WithdrawRequest handling if needed
        if (dto.getWithdrawRequestId() != null) {
            transaction.setWithdrawRequest(withdrawRequestRepository.getReferenceById(dto.getWithdrawRequestId()));
        }

        return transaction;
    }
}
