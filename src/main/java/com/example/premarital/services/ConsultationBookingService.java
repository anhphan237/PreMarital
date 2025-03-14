package com.example.premarital.services;

import com.example.premarital.dtos.ConsultationBookingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsultationBookingService {
    Page<ConsultationBookingDTO> getConsultationBookings(Pageable pageable);
    void createConsultationBooking(ConsultationBookingDTO dto);
    ConsultationBookingDTO getConsultationBookingById(Long id);
    boolean deleteConsultationBookingById(Long id);
    boolean updateConsultationBooking(Long id, ConsultationBookingDTO updatedConsultationBookingDTO);
}
