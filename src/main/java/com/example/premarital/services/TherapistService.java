package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.models.Therapist;

public interface TherapistService {
    PagingResult<TherapistDTO> getTherapists(PaginationRequest request);
    TherapistDTO createTherapist(TherapistDTO dto);
    Therapist getTherapistById(Long id);
    boolean deleteTherapistById(Long id);
    boolean updateTherapist(Long id, TherapistDTO updatedTherapistDTO);
}
