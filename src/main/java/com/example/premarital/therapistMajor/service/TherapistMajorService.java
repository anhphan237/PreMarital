package com.example.premarital.therapistMajor.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapist.dto.TherapistDTO;
import com.example.premarital.therapistMajor.dto.TherapistMajorDTO;
import com.example.premarital.therapistMajor.model.TherapistMajor;

public interface TherapistMajorService {
    PagingResult<TherapistMajorDTO> getTherapistMajors(PaginationRequest request);
    TherapistMajorDTO createTherapistMajor(TherapistMajorDTO dto);
    TherapistMajor getTherapistMajorById(Long id);
    boolean deleteTherapistMajorById(Long id);
    boolean updateTherapistMajor(Long id, TherapistDTO updatedTherapistDTO);
}
