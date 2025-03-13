package com.example.premarital.repositories;

import com.example.premarital.models.ConsultationBooking;
import com.example.premarital.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationBookingRepository extends JpaRepository<ConsultationBooking, Long> {
    Page<ConsultationBooking> findBookingsByIsActiveTrue(Pageable pageable);
}
