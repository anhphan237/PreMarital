package com.example.premarital.services.impl;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.mappers.ConsultationBookingMapper;
import com.example.premarital.models.Category;
import com.example.premarital.models.TherapistSchedule;
import com.example.premarital.services.ConsultationBookingService;
import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.models.ConsultationBooking;
import com.example.premarital.repositories.ConsultationBookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ConsultationBookingServiceImpl implements ConsultationBookingService {
    private final ConsultationBookingRepository consultationBookingRepository;
    private final ConsultationBookingMapper consultationBookingMapper;

    public ConsultationBookingServiceImpl(ConsultationBookingRepository consultationBookingRepository, ConsultationBookingMapper consultationBookingMapper) {
        this.consultationBookingRepository = consultationBookingRepository;
        this.consultationBookingMapper = consultationBookingMapper;
    }

    @Override
    public Page<ConsultationBookingDTO> getConsultationBookings(Pageable pageable) {
        Page<ConsultationBooking> entities = consultationBookingRepository.findAll(pageable);
        Page<ConsultationBookingDTO> dtoPage = entities.map(new Function<ConsultationBooking, ConsultationBookingDTO>() {

            @Override
            public ConsultationBookingDTO apply(ConsultationBooking consultationBooking) {
                ConsultationBookingDTO dto = consultationBookingMapper.toDTO(consultationBooking);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createConsultationBooking(ConsultationBookingDTO dto) {
        ConsultationBooking consultationBooking = consultationBookingMapper.toEntity(dto);
        consultationBookingRepository.save(consultationBooking);
    }

    @Override
    public ConsultationBookingDTO getConsultationBookingById(Long id) {
        return consultationBookingMapper.toDTO(consultationBookingRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteConsultationBookingById(Long id) {
        return consultationBookingRepository.findById(id).map(consultationBooking -> {
            consultationBooking.setIsActive(false);
            consultationBookingRepository.save(consultationBooking);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateConsultationBooking(Long id, ConsultationBookingDTO updatedConsultationBookingDTO) {
        return consultationBookingRepository.findById(id).map(consultationBooking -> {
            ConsultationBooking updatedConsultationBooking = consultationBookingMapper.toEntityWithId(id, updatedConsultationBookingDTO);
            consultationBookingRepository.save(updatedConsultationBooking);
            return true;
        }).orElse(false);
    }
}
