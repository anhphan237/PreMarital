package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.mappers.TherapistMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.services.TherapistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TherapistServiceImpl implements TherapistService {
    private final TherapistRepository therapistRepository;
    private final TherapistMapper therapistMapper;

    public TherapistServiceImpl(TherapistRepository therapistRepository, TherapistMapper therapistMapper) {
        this.therapistRepository = therapistRepository;
        this.therapistMapper = therapistMapper;
    }

    @Override
    public Page<TherapistDTO> getTherapists(Pageable pageable) {
        Page<Therapist> entities = therapistRepository.findAll(pageable);
        Page<TherapistDTO> dtoPage = entities.map(new Function<Therapist, TherapistDTO>() {

            @Override
            public TherapistDTO apply(Therapist therapist) {
                TherapistDTO dto = new TherapistDTO();
                dto.setTherapistMajorId(therapist.getTherapistMajor() == null ? null : therapist.getTherapistMajor().getId());
                dto.setBio(therapist.getBio());
                dto.setCertificationExpirationDate(therapist.getCertificationExpirationDate());
                dto.setCertificationIssueDate(therapist.getCertificationIssueDate());
                dto.setCertificationIssueDate(therapist.getCertificationIssueDate());
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public TherapistDTO createTherapist(TherapistDTO dto) {
        Therapist therapist = therapistMapper.toEntity(dto);
        therapistRepository.save(therapist);
        return therapistMapper.toDTO(therapist);
    }

    @Override
    public TherapistDTO getTherapistById(Long id) {
        return therapistMapper.toDTO(therapistRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteTherapistById(Long id) {
        return therapistRepository.findById(id).map(therapist -> {
            therapist.setIsActive(false);
            therapistRepository.save(therapist);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateTherapist(Long id, TherapistDTO updatedTherapistDTO) {
        return therapistRepository.findById(id).map(therapist -> {
            Therapist updatedTherapist = therapistMapper.toEntityWithId(id, updatedTherapistDTO);
            therapistRepository.save(updatedTherapist);
            return true;
        }).orElse(false);
    }
}
