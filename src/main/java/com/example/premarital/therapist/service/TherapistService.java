package com.example.premarital.therapist.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapist.dto.TherapistDTO;
import com.example.premarital.therapist.model.Therapist;
import org.springframework.stereotype.Service;

public interface TherapistService {
    PagingResult<TherapistDTO> getTherapists(PaginationRequest request);
    TherapistDTO createTherapist(TherapistDTO dto);
    Therapist getTherapistById(Long id);
    boolean deleteTherapistById(Long id);
    boolean updateTherapist(Long id, TherapistDTO updatedTherapistDTO);
}
