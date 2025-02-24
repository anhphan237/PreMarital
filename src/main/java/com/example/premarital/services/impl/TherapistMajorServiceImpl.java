package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.repositories.TherapistMajorRepository;
import com.example.premarital.services.TherapistMajorService;
import org.springframework.stereotype.Service;

@Service
public class TherapistMajorServiceImpl implements TherapistMajorService {
    private final TherapistMajorRepository therapistMajorRepository;

    public TherapistMajorServiceImpl(TherapistMajorRepository therapistMajorRepository) {
        this.therapistMajorRepository = therapistMajorRepository;
    }

    @Override
    public PagingResult<TherapistMajorDTO> getTherapistMajors(PaginationRequest request) {
        return null;
    }

    @Override
    public TherapistMajorDTO createTherapistMajor(TherapistMajorDTO dto) {
        return null;
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
