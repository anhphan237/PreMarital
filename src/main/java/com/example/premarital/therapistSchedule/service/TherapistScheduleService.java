package com.example.premarital.therapistSchedule.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapistSchedule.dto.TherapistScheduleDTO;
import com.example.premarital.therapistSchedule.model.TherapistSchedule;

public interface TherapistScheduleService{
    PagingResult<TherapistScheduleDTO> getTherapistSchedules(PaginationRequest request);
    TherapistScheduleDTO createTherapistSchedule(TherapistScheduleDTO dto);
    TherapistSchedule getTherapistScheduleById(Long id);
    boolean deleteTherapistScheduleById(Long id);
    boolean updateTherapistSchedule(Long id, TherapistScheduleDTO updatedTherapistScheduleDTO);
}
