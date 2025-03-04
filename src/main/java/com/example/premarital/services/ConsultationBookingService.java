package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.models.ConsultationBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsultationBookingService {
    Page<ConsultationBookingDTO> getConsultationBookings(Pageable pageable);
    ConsultationBookingDTO createConsultationBooking(ConsultationBookingDTO dto);
    ConsultationBooking getConsultationBookingById(Long id);
    boolean deleteConsultationBookingById(Long id);
    boolean updateConsultationBooking(Long id, ConsultationBookingDTO updatedConsultationBookingDTO);
}
