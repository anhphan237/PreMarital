package com.example.premarital.mappers;

import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.models.ConsultationBooking;

public interface ConsultationBookingMapper {
    ConsultationBookingDTO toDTO(ConsultationBooking consultationBooking);
    ConsultationBooking toEntity(ConsultationBookingDTO dto);
    ConsultationBooking toEntityWithId(Long id, ConsultationBookingDTO dto);
}
