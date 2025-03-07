package com.example.premarital.mappers;

import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.models.BankAccount;

public interface BankAccountMapper {
    BankAccountDTO toDTO(BankAccount bankAccount);
    BankAccount toEntity(BankAccountDTO dto);
    BankAccount toEntityWithId(Long id, BankAccountDTO dto);
}
