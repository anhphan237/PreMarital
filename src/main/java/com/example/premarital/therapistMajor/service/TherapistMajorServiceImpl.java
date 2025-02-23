package com.example.premarital.therapistMajor.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapist.dto.TherapistDTO;
import com.example.premarital.therapistMajor.dto.TherapistMajorDTO;
import com.example.premarital.therapistMajor.model.TherapistMajor;
import com.example.premarital.therapistMajor.repository.TherapistMajorRepository;
import org.springframework.stereotype.Service;

@Service
public class TherapistMajorServiceImpl implements TherapistMajorService{
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
