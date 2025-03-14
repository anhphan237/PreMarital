package com.example.premarital.repositories;

import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRequestRepository extends JpaRepository<WithdrawRequest, Long> {
    Page<WithdrawRequest> findWithdrawRequestsByIsActiveTrue(Pageable pageable);
}
