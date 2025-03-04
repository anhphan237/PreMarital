package com.example.premarital.services;

import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.models.BankAccount;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankAccountService{
    Page<BankAccountDTO> getBankAccounts(Pageable pageable);
    void createBankAccount(BankAccountDTO dto);
    BankAccount getBankAccountById(Long id);
    boolean deleteBankAccountById(Long id);
    boolean updateBankAccount(Long id, BankAccountDTO updatedBankAccountDTO);
}
