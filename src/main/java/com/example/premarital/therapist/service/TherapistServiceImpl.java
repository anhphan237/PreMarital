package com.example.premarital.therapist.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PaginationUtils;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapist.dto.TherapistDTO;
import com.example.premarital.therapist.mapper.TherapistMapper;
import com.example.premarital.therapist.model.Therapist;
import com.example.premarital.therapist.repository.TherapistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TherapistServiceImpl implements TherapistService {
    private final TherapistRepository therapistRepository;
    private final TherapistMapper therapistMapper;

    public TherapistServiceImpl(TherapistRepository therapistRepository, TherapistMapper therapistMapper) {
        this.therapistRepository = therapistRepository;
        this.therapistMapper = therapistMapper;
    }

    @Override
    public PagingResult<TherapistDTO> getTherapists(PaginationRequest request) {
        final Pageable pageable = PaginationUtils.getPageable(request);
        final Page<Therapist> entities = therapistRepository.findAll(pageable);
        final List<TherapistDTO> entitiesDto = entities.stream().map(therapistMapper::toDTO).toList();
        return new PagingResult<>(
                entitiesDto,
                entities.getTotalPages(),
                entities.getTotalElements(),
                entities.getSize(),
                entities.getNumber(),
                entities.isEmpty()
        );
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
