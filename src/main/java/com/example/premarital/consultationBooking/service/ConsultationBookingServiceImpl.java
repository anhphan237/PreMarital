package com.example.premarital.consultationBooking.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.consultationBooking.dto.ConsultationBookingDTO;
import com.example.premarital.consultationBooking.model.ConsultationBooking;
import com.example.premarital.consultationBooking.repository.ConsultationBookingRepository;

public class ConsultationBookingServiceImpl implements ConsultationBookingService {
    private final ConsultationBookingRepository consultationBookingRepository;

    public ConsultationBookingServiceImpl(ConsultationBookingRepository consultationBookingRepository) {
        this.consultationBookingRepository = consultationBookingRepository;
    }

    @Override
    public PagingResult<ConsultationBookingDTO> getConsultationBookings(PaginationRequest request) {
        return null;
    }

    @Override
    public ConsultationBookingDTO createConsultationBooking(ConsultationBookingDTO dto) {
        return null;
    }

    @Override
    public ConsultationBooking getConsultationBookingById(Long id) {
        return null;
    }

    @Override
    public boolean deleteConsultationBookingById(Long id) {
        return false;
    }

    @Override
    public boolean updateConsultationBooking(Long id, ConsultationBookingDTO updatedConsultationBookingDTO) {
        return false;
    }
}
