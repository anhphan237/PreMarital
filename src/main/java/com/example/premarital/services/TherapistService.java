package com.example.premarital.services;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.models.Therapist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TherapistService {
    Page<TherapistDTO> getTherapists(Pageable pageable);
    TherapistDTO createTherapist(TherapistDTO dto);
    Therapist getTherapistById(Long id);
    boolean deleteTherapistById(Long id);
    boolean updateTherapist(Long id, TherapistDTO updatedTherapistDTO);
}
