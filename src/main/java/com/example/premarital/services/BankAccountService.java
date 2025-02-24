package com.example.premarital.services;

import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.models.BankAccount;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface BankAccountService{
    PagingResult<BankAccountDTO> getBankAccounts(PaginationRequest request);
    BankAccountDTO createBankAccount(BankAccountDTO dto);
    BankAccount getBankAccountById(Long id);
    boolean deleteBankAccountById(Long id);
    boolean updateBankAccount(Long id, BankAccountDTO updatedBankAccountDTO);
}
