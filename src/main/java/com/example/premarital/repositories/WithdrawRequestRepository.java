package com.example.premarital.repositories;

import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRequestRepository extends JpaRepository<WithdrawRequest, Long> {
}
