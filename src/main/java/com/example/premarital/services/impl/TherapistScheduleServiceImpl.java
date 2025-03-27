package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.TherapistScheduleMapper;
import com.example.premarital.models.ConsultationBooking;
import com.example.premarital.models.TherapistSchedule;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.TherapistScheduleRepository;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.TherapistScheduleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class TherapistScheduleServiceImpl implements TherapistScheduleService {
    private final TherapistScheduleRepository therapistScheduleRepository;
    private final TherapistScheduleMapper therapistScheduleMapper;
    private final TherapistRepository therapistRepository;
    private static final Logger logger = LoggerFactory.getLogger(TherapistScheduleServiceImpl.class);

    @Override
    public Page<TherapistScheduleDTO> getTherapistSchedules(Pageable pageable) {
        Page<TherapistSchedule> schedules = therapistScheduleRepository.findSchedulesByIsActiveTrue(pageable);
        if (schedules.isEmpty()) {
            logger.warn("No schedules actively found in the system");
        }
        return schedules.map(therapistScheduleMapper::toDTO);
    }

    @Override
    @Transactional
    public void createTherapistSchedule(TherapistScheduleDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when create");
        }

        therapistRepository.findById(dto.getTherapistId())
                .orElseThrow(() -> new InvalidDataException("Invalid therapistId: " + dto.getTherapistId()));

        if (dto.getAvailableDate() == null || dto.getStartTime() == null || dto.getEndTime() == null) {
            throw new InvalidDataException("Available date, start time, and end time are required.");
        }

        if (dto.getEndTime().isBefore(dto.getStartTime())) {
            throw new InvalidDataException("End time must be after start time.");
        }

        try {
            TherapistSchedule therapistSchedule = therapistScheduleMapper.toEntity(dto);
            therapistSchedule.setBooked(false);
            therapistSchedule.setActive(true);
            therapistScheduleRepository.save(therapistSchedule);
            logger.info("Therapist's schedule created successfully with ID: {}", therapistSchedule.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating therapist's schedule: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid therapist's schedule data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating therapist's schedule: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create therapist's schedule", e);
        }
    }

    @Override
    public TherapistScheduleDTO getTherapistScheduleById(Long id) {
        return therapistScheduleRepository.findById(id)
                .map(therapistScheduleMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Schedule with ID " + id + " not found"));
    }

    @Override
    public Page<TherapistScheduleDTO> getTherapistScheduleByTherapistId(Long therapistId, Pageable pageable) {
        Page<TherapistSchedule> schedules = therapistScheduleRepository.findSchedulesByTherapistId(therapistId, pageable);
        if (schedules.isEmpty()) {
            logger.warn("No schedules actively found in the system");
        }
        return schedules.map(therapistScheduleMapper::toDTO);
    }

    @Override
    @Transactional
    public boolean deleteTherapistScheduleById(Long id) {
        TherapistSchedule therapistSchedule = therapistScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TherapistSchedule with ID " + id + " not found"));

        if (!therapistSchedule.isActive()) {
            logger.warn("TherapistSchedule with ID {} is already inactive", id);
            return false;
        }

        try {
            therapistSchedule.setActive(false);
            therapistScheduleRepository.save(therapistSchedule);
            logger.info("TherapistSchedule with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating TherapistSchedule with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate therapist schedule", e);
        }
    }

    @Override
    @Transactional
    public boolean updateTherapistSchedule(Long id, TherapistScheduleDTO dto) {
        TherapistSchedule existingSchedule = therapistScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TherapistSchedule with ID " + id + " not found"));

        if (!therapistRepository.existsById(dto.getTherapistId())) {
            throw new EntityNotFoundException("Therapist with ID " + dto.getTherapistId() + " not found");
        }

        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new InvalidDataException("Start time must be before end time");
        }

        try {
            TherapistSchedule updatedTherapistSchedule = therapistScheduleMapper.toEntityWithId(id, dto);
            therapistScheduleRepository.save(updatedTherapistSchedule);
            logger.info("TherapistSchedule with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating TherapistSchedule with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating TherapistSchedule with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update TherapistSchedule", e);
        }
    }
}
