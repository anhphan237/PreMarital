package com.example.premarital.consultationBooking.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.consultationBooking.dto.ConsultationBookingDTO;
import com.example.premarital.consultationBooking.model.ConsultationBooking;
import com.example.premarital.therapistSchedule.dto.TherapistScheduleDTO;
import com.example.premarital.therapistSchedule.model.TherapistSchedule;

public interface ConsultationBookingService {
    PagingResult<ConsultationBookingDTO> getConsultationBookings(PaginationRequest request);
    ConsultationBookingDTO createConsultationBooking(ConsultationBookingDTO dto);
    ConsultationBooking getConsultationBookingById(Long id);
    boolean deleteConsultationBookingById(Long id);
    boolean updateConsultationBooking(Long id, ConsultationBookingDTO updatedConsultationBookingDTO);
}
