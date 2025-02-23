package com.example.premarital.withdrawRequest.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapist.dto.TherapistDTO;
import com.example.premarital.therapistMajor.dto.TherapistMajorDTO;
import com.example.premarital.therapistMajor.model.TherapistMajor;
import com.example.premarital.withdrawRequest.dto.WithdrawRequestDTO;
import com.example.premarital.withdrawRequest.model.WithdrawRequest;

public interface WithdrawRequestService {
    PagingResult<WithdrawRequestDTO> getWithdrawRequests(PaginationRequest request);
    WithdrawRequestDTO createWithdrawRequest(WithdrawRequestDTO dto);
    WithdrawRequest getWithdrawRequestById(Long id);
    boolean deleteWithdrawRequestById(Long id);
    boolean updateWithdrawRequest(Long id, WithdrawRequestDTO updatedWithdrawRequestDTO);
}
