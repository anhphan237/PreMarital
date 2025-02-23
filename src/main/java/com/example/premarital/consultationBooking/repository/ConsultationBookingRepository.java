package com.example.premarital.consultationBooking.repository;

import com.example.premarital.consultationBooking.model.ConsultationBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationBookingRepository extends JpaRepository<ConsultationBooking, Long> {
}
