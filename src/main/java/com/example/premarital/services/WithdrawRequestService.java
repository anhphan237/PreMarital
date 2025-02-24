package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.models.WithdrawRequest;

public interface WithdrawRequestService {
    PagingResult<WithdrawRequestDTO> getWithdrawRequests(PaginationRequest request);
    WithdrawRequestDTO createWithdrawRequest(WithdrawRequestDTO dto);
    WithdrawRequest getWithdrawRequestById(Long id);
    boolean deleteWithdrawRequestById(Long id);
    boolean updateWithdrawRequest(Long id, WithdrawRequestDTO updatedWithdrawRequestDTO);
}
