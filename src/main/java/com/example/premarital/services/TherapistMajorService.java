package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.models.TherapistMajor;

public interface TherapistMajorService {
    PagingResult<TherapistMajorDTO> getTherapistMajors(PaginationRequest request);
    TherapistMajorDTO createTherapistMajor(TherapistMajorDTO dto);
    TherapistMajor getTherapistMajorById(Long id);
    boolean deleteTherapistMajorById(Long id);
    boolean updateTherapistMajor(Long id, TherapistDTO updatedTherapistDTO);
}
