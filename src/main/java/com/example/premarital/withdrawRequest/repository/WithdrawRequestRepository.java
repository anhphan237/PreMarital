package com.example.premarital.withdrawRequest.repository;

import com.example.premarital.withdrawRequest.model.WithdrawRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRequestRepository extends JpaRepository<WithdrawRequest, Long> {
}
