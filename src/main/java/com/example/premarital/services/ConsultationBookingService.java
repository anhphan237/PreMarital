package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.models.ConsultationBooking;

public interface ConsultationBookingService {
    PagingResult<ConsultationBookingDTO> getConsultationBookings(PaginationRequest request);
    ConsultationBookingDTO createConsultationBooking(ConsultationBookingDTO dto);
    ConsultationBooking getConsultationBookingById(Long id);
    boolean deleteConsultationBookingById(Long id);
    boolean updateConsultationBooking(Long id, ConsultationBookingDTO updatedConsultationBookingDTO);
}
