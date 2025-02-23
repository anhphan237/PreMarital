package com.example.premarital.therapistSchedule.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapistSchedule.dto.TherapistScheduleDTO;
import com.example.premarital.therapistSchedule.model.TherapistSchedule;
import com.example.premarital.therapistSchedule.repository.TherapistScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class TherapistScheduleServiceImpl implements TherapistScheduleService {
    private final TherapistScheduleRepository therapistScheduleRepository;

    public TherapistScheduleServiceImpl(TherapistScheduleRepository therapistScheduleRepository) {
        this.therapistScheduleRepository = therapistScheduleRepository;
    }

    @Override
    public PagingResult<TherapistScheduleDTO> getTherapistSchedules(PaginationRequest request) {
        return null;
    }

    @Override
    public TherapistScheduleDTO createTherapistSchedule(TherapistScheduleDTO dto) {
        return null;
    }

    @Override
    public TherapistSchedule getTherapistScheduleById(Long id) {
        return null;
    }

    @Override
    public boolean deleteTherapistScheduleById(Long id) {
        return false;
    }

    @Override
    public boolean updateTherapistSchedule(Long id, TherapistScheduleDTO updatedTherapistScheduleDTO) {
        return false;
    }
}
