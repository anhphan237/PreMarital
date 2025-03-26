package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.TherapistMajorMapper;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.repositories.TherapistMajorRepository;
import com.example.premarital.services.TherapistMajorService;
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
public class TherapistMajorServiceImpl implements TherapistMajorService {
    private final TherapistMajorRepository therapistMajorRepository;
    private final TherapistMajorMapper therapistMajorMapper;
    private static final Logger logger = LoggerFactory.getLogger(TherapistMajorServiceImpl.class);

    @Override
    public Page<TherapistMajorDTO> getTherapistMajors(Pageable pageable) {
        Page<TherapistMajor> majors = therapistMajorRepository.findTherapistMajorsByIsActiveTrue(pageable);
        if (majors.isEmpty()) {
            logger.warn("No therapist majors found in the system");
        }
        return majors.map(therapistMajorMapper::toDTO);
    }

    @Override
    @Transactional
    public void createTherapistMajor(TherapistMajorDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when creating a new therapist major");
        }

        try {
            TherapistMajor therapistMajor = therapistMajorMapper.toEntity(dto);
            therapistMajorRepository.save(therapistMajor);
            logger.info("Therapist major created successfully with ID: {}", therapistMajor.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating therapist major: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid therapist major data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating therapist major: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create therapist major", e);
        }
    }

    @Override
    public TherapistMajorDTO getTherapistMajorById(Long id) {
        return therapistMajorRepository.findById(id)
                .map(therapistMajorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Therapist Major with ID " + id + " not found"));
    }

    @Override
    public boolean deleteTherapistMajorById(Long id) {
        TherapistMajor therapistMajor = therapistMajorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Therapist Major with ID " + id + " not found"));

        if (!therapistMajor.getIsActive()) {
            logger.warn("Therapist Major with ID {} is already inactive", id);
            return false;
        }

        try {
            therapistMajor.setIsActive(false);
            therapistMajorRepository.save(therapistMajor);
            logger.info("Therapist Major with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating therapist major with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate therapist major", e);
        }
    }

    @Override
    public boolean updateTherapistMajor(Long id, TherapistMajorDTO updatedTherapistDTO) {
        if (updatedTherapistDTO.getId() != null) {
            throw new InvalidDataException("ID must be null when updating therapist major");
        }

        if (!therapistMajorRepository.existsById(id)) {
            throw new EntityNotFoundException("Therapist Major ID " + id + " not found");
        }

        try {
            TherapistMajor updatedTherapistMajor = therapistMajorMapper.toEntityWithId(id, updatedTherapistDTO);
            therapistMajorRepository.save(updatedTherapistMajor);
            logger.info("Therapist Major with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating therapist major with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating therapist major with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update therapist major", e);
        }
    }
}
