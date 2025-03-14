package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.mappers.WithdrawRequestMapper;
import com.example.premarital.models.WithdrawRequest;
import com.example.premarital.repositories.TransactionRepository;
import com.example.premarital.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class WithdrawRequestMapperImpl implements WithdrawRequestMapper {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public WithdrawRequestMapperImpl(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public WithdrawRequestDTO toDTO(WithdrawRequest withdrawRequest) {
        if (withdrawRequest == null) {
            return null;
        }
        return new WithdrawRequestDTO(
                withdrawRequest.getId(),
                withdrawRequest.getUser() != null ? withdrawRequest.getUser().getId() : null,
                withdrawRequest.getRequestAmount(),
                withdrawRequest.getRequestDate(),
                withdrawRequest.isApproved(),
                withdrawRequest.getApprovedBy(),
                withdrawRequest.getApprovedDate(),
                withdrawRequest.getTransaction() == null ? 0 : withdrawRequest.getTransaction().getId(),
                withdrawRequest.getIsActive()
        );
    }

    @Override
    public WithdrawRequest toEntity(WithdrawRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setId(dto.getId());
        withdrawRequest.setRequestAmount(dto.getRequestAmount());
        withdrawRequest.setRequestDate(dto.getRequestDate());
        withdrawRequest.setApproved(dto.getIsApproved());
        withdrawRequest.setApprovedBy(dto.getApprovedBy());
        withdrawRequest.setApprovedDate(dto.getApprovedDate());
        withdrawRequest.setUser(userRepository.getReferenceById(dto.getUserId()));
        withdrawRequest.setTransaction(dto.getTransactionId() == null ? null : transactionRepository.getReferenceById(dto.getTransactionId()));
        withdrawRequest.setIsActive(dto.getIsActive());

        return withdrawRequest;
    }

    @Override
    public WithdrawRequest toEntityWithId(Long id, WithdrawRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setId(id);
        withdrawRequest.setRequestAmount(dto.getRequestAmount());
        withdrawRequest.setRequestDate(dto.getRequestDate());
        withdrawRequest.setApproved(dto.getIsApproved());
        withdrawRequest.setApprovedBy(dto.getApprovedBy());
        withdrawRequest.setApprovedDate(dto.getApprovedDate());
        withdrawRequest.setUser(userRepository.getReferenceById(dto.getUserId()));
        withdrawRequest.setTransaction(dto.getTransactionId() == null ? null : transactionRepository.getReferenceById(dto.getTransactionId()));
        withdrawRequest.setIsActive(dto.getIsActive());

        return withdrawRequest;
    }
}
