package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.mappers.ConsultationBookingMapper;
import com.example.premarital.models.ConsultationBooking;
import com.example.premarital.repositories.*;
import org.springframework.stereotype.Component;

@Component
public class ConsultationBookingMapperImpl implements ConsultationBookingMapper {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TherapistScheduleRepository therapistScheduleRepository;
    private final ConsultationBookingRepository consultationBookingRepository;

    public ConsultationBookingMapperImpl(CategoryRepository categoryRepository, UserRepository userRepository, TransactionRepository transactionRepository, TherapistScheduleRepository therapistScheduleRepository, ConsultationBookingRepository consultationBookingRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.therapistScheduleRepository = therapistScheduleRepository;
        this.consultationBookingRepository = consultationBookingRepository;
    }

    @Override
    public ConsultationBookingDTO toDTO(ConsultationBooking consultationBooking) {
        if (consultationBooking == null) {
            return null;
        }

        ConsultationBookingDTO dto = new ConsultationBookingDTO();
        dto.setId(consultationBooking.getId());
        dto.setAmount(consultationBooking.getAmount());
        dto.setStatus(consultationBooking.getStatus());
        dto.setMeetUrl(consultationBooking.getMeetUrl());
        dto.setIsActive(consultationBooking.getIsActive());

        if (consultationBooking.getCategory() != null) {
            dto.setCategoryId(consultationBooking.getCategory().getId());
        }
        if (consultationBooking.getUser() != null) {
            dto.setUserId(consultationBooking.getUser().getId());
        }
        if (consultationBooking.getTherapistSchedule() != null) {
            dto.setTherapistScheduleId(consultationBooking.getTherapistSchedule().getId());
        }

        return dto;
    }

    @Override
    public ConsultationBooking toEntity(ConsultationBookingDTO dto) {
        if (dto == null) {
            return null;
        }

        ConsultationBooking consultationBooking = (dto.getId() != null)
                ? consultationBookingRepository.findById(dto.getId()).orElse(new ConsultationBooking())
                : new ConsultationBooking();

        consultationBooking.setId(dto.getId());
        consultationBooking.setAmount(dto.getAmount());
        consultationBooking.setStatus(dto.getStatus());
        consultationBooking.setMeetUrl(dto.getMeetUrl());
        consultationBooking.setIsActive(dto.getIsActive());

        if (dto.getCategoryId() != null) {
            consultationBooking.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        }
        if (dto.getUserId() != null) {
            consultationBooking.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        }
        if (dto.getTherapistScheduleId() != null) {
            consultationBooking.setTherapistSchedule(therapistScheduleRepository.findById(dto.getTherapistScheduleId()).orElse(null));
        }

        return consultationBooking;
    }

    @Override
    public ConsultationBooking toEntityWithId(Long id, ConsultationBookingDTO dto) {
        if (dto == null) {
            return null;
        }

        ConsultationBooking consultationBooking = (dto.getId() != null)
                ? consultationBookingRepository.findById(dto.getId()).orElse(new ConsultationBooking())
                : new ConsultationBooking();

        consultationBooking.setId(id);
        consultationBooking.setAmount(dto.getAmount());
        consultationBooking.setStatus(dto.getStatus());
        consultationBooking.setMeetUrl(dto.getMeetUrl());
        consultationBooking.setIsActive(dto.getIsActive());

        if (dto.getCategoryId() != null) {
            consultationBooking.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        }
        if (dto.getUserId() != null) {
            consultationBooking.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        }
        if (dto.getTherapistScheduleId() != null) {
            consultationBooking.setTherapistSchedule(therapistScheduleRepository.findById(dto.getTherapistScheduleId()).orElse(null));
        }

        return consultationBooking;
    }
}
