package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.TherapistMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.services.TherapistService;
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
public class TherapistServiceImpl implements TherapistService {
    private final TherapistRepository therapistRepository;
    private final TherapistMapper therapistMapper;
    private static final Logger logger = LoggerFactory.getLogger(TherapistServiceImpl.class);

    @Override
    public Page<TherapistDTO> getTherapists(Pageable pageable) {
        Page<Therapist> therapists = therapistRepository.findTherapistsByIsActiveTrue(pageable);
        if (therapists.isEmpty()) {
            logger.warn("No therapists found in the system");
        }
        return therapists.map(therapistMapper::toDTO);
    }

    @Override
    public TherapistDTO getTherapistById(Long id) {
        return therapistRepository.findById(id)
                .map(therapistMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Therapist with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteTherapistById(Long id) {
        Therapist therapist = therapistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Therapist with ID " + id + " not found"));

        if (!therapist.getIsActive()) {
            logger.warn("Therapist with ID {} is already inactive", id);
            return false;
        }

        try {
            therapist.setIsActive(false);
            therapistRepository.save(therapist);
            logger.info("Therapist with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating therapist with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate therapist", e);
        }
    }

    @Override
    @Transactional
    public boolean updateTherapist(Long id, TherapistDTO updatedTherapistDTO) {
        if (updatedTherapistDTO.getUserId() != null) {
            throw new InvalidDataException("ID must be null when updating therapist");
        }

        if (!therapistRepository.existsById(id)) {
            throw new EntityNotFoundException("Therapist ID " + id + " not found");
        }

        try {
            Therapist updatedTherapist = therapistMapper.toEntityWithId(id, updatedTherapistDTO);
            therapistRepository.save(updatedTherapist);
            logger.info("Therapist with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating therapist with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating therapist with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update therapist", e);
        }
    }
}
