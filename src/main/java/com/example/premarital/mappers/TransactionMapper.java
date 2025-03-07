package com.example.premarital.mappers;

import com.example.premarital.dtos.TransactionDTO;
import com.example.premarital.models.Transaction;

public interface TransactionMapper {
    TransactionDTO toDTO(Transaction transaction);
    Transaction toEntity(TransactionDTO dto);
    Transaction toEntityWithId(Long id, TransactionDTO dto);
}
