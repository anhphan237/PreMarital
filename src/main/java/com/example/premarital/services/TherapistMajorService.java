package com.example.premarital.services;

import com.example.premarital.dtos.TherapistMajorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TherapistMajorService {
    Page<TherapistMajorDTO> getTherapistMajors(Pageable pageable);
    void createTherapistMajor(TherapistMajorDTO dto);
    TherapistMajorDTO getTherapistMajorById(Long id);
    boolean deleteTherapistMajorById(Long id);
    boolean updateTherapistMajor(Long id, TherapistMajorDTO updatedTherapistDTO);
}
