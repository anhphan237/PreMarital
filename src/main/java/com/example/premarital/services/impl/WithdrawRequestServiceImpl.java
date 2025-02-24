package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.models.WithdrawRequest;
import com.example.premarital.repositories.WithdrawRequestRepository;
import com.example.premarital.services.WithdrawRequestService;
import org.springframework.stereotype.Service;

@Service
public class WithdrawRequestServiceImpl implements WithdrawRequestService {
    private final WithdrawRequestRepository withdrawRequestRepository;

    public WithdrawRequestServiceImpl(WithdrawRequestRepository withdrawRequestRepository) {
        this.withdrawRequestRepository = withdrawRequestRepository;
    }

    @Override
    public PagingResult<WithdrawRequestDTO> getWithdrawRequests(PaginationRequest request) {
        return null;
    }

    @Override
    public WithdrawRequestDTO createWithdrawRequest(WithdrawRequestDTO dto) {
        return null;
    }

    @Override
    public WithdrawRequest getWithdrawRequestById(Long id) {
        return null;
    }

    @Override
    public boolean deleteWithdrawRequestById(Long id) {
        return false;
    }

    @Override
    public boolean updateWithdrawRequest(Long id, WithdrawRequestDTO updatedWithdrawRequestDTO) {
        return false;
    }
}
