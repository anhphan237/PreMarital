package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.dtos.UserDTO;
import com.example.premarital.mappers.TherapistMajorMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.models.User;
import com.example.premarital.repositories.TherapistMajorRepository;
import com.example.premarital.services.TherapistMajorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TherapistMajorServiceImpl implements TherapistMajorService {
    private final TherapistMajorRepository therapistMajorRepository;
    private final TherapistMajorMapper therapistMajorMapper;

    public TherapistMajorServiceImpl(TherapistMajorRepository therapistMajorRepository, TherapistMajorMapper therapistMajorMapper) {
        this.therapistMajorRepository = therapistMajorRepository;
        this.therapistMajorMapper = therapistMajorMapper;
    }

    @Override
    public Page<TherapistMajorDTO> getTherapistMajors(Pageable pageable) {
        Page<TherapistMajor> entities = therapistMajorRepository.findAll(pageable);
        Page<TherapistMajorDTO> dtoPage = entities.map(new Function<TherapistMajor, TherapistMajorDTO>() {
            @Override
            public TherapistMajorDTO apply(TherapistMajor therapistMajor) {
                TherapistMajorDTO dto = new TherapistMajorDTO();
                dto.setId(therapistMajor.getId());
                dto.setName(therapistMajor.getName());
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createTherapistMajor(TherapistMajorDTO dto) {
        therapistMajorRepository.save(therapistMajorMapper.toEntity(dto));
    }

    @Override
    public TherapistMajor getTherapistMajorById(Long id) {
        return null;
    }

    @Override
    public boolean deleteTherapistMajorById(Long id) {
        return false;
    }

    @Override
    public boolean updateTherapistMajor(Long id, TherapistDTO updatedTherapistDTO) {
        return false;
    }
}
