package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.services.ConsultationBookingService;
import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.models.ConsultationBooking;
import com.example.premarital.repositories.ConsultationBookingRepository;

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
