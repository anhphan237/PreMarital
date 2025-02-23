package com.example.premarital.withdrawRequest.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.withdrawRequest.dto.WithdrawRequestDTO;
import com.example.premarital.withdrawRequest.model.WithdrawRequest;
import com.example.premarital.withdrawRequest.repository.WithdrawRequestRepository;
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
