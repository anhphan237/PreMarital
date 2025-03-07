package com.example.premarital.services;

import com.example.premarital.dtos.TherapistScheduleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TherapistScheduleService{
    Page<TherapistScheduleDTO> getTherapistSchedules(Pageable pageable);
    void createTherapistSchedule(TherapistScheduleDTO dto);
    TherapistScheduleDTO getTherapistScheduleById(Long id);
    boolean deleteTherapistScheduleById(Long id);
    boolean updateTherapistSchedule(Long id, TherapistScheduleDTO updatedTherapistScheduleDTO);
}
