package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PaginationUtils;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.mappers.TherapistMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.services.TherapistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
                dto.setId(therapist.getId());
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
    public Therapist getTherapistById(Long id) {
        return therapistRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteTherapistById(Long id) {
        return therapistRepository.findById(id).map(therapist -> {
            therapistRepository.delete(therapist);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateTherapist(Long id, TherapistDTO updatedTherapistDTO) {
        return therapistRepository.findById(id).map(therapist -> {
            updatedTherapistDTO.setId(therapist.getId());
            updatedTherapistDTO.setBio(therapist.getBio());
            updatedTherapistDTO.setUserId(therapist.getUser().getId());
            updatedTherapistDTO.setCertificationExpirationDate(therapist.getCertificationExpirationDate());
            updatedTherapistDTO.setTherapistCertificationName(therapist.getTherapistCertificationName());
            updatedTherapistDTO.setCertificationIssueDate(therapist.getCertificationIssueDate());
            updatedTherapistDTO.setCertificationIssuedBy(therapist.getCertificationIssuedBy());
            return true;
        }).orElse(false);
    }
}
