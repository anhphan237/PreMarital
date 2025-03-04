package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.mappers.TherapistScheduleMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.TherapistSchedule;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.TherapistScheduleRepository;
import com.example.premarital.services.TherapistScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class TherapistScheduleServiceImpl implements TherapistScheduleService {
    private final TherapistScheduleRepository therapistScheduleRepository;
    private final TherapistRepository therapistRepository;
    private final TherapistScheduleMapper therapistScheduleMapper;

    public TherapistScheduleServiceImpl(TherapistScheduleRepository therapistScheduleRepository, TherapistScheduleMapper therapistScheduleMapper, TherapistRepository therapistRepository) {
        this.therapistScheduleRepository = therapistScheduleRepository;
        this.therapistScheduleMapper = therapistScheduleMapper;
        this.therapistRepository = therapistRepository;
    }

    @Override
    public Page<TherapistScheduleDTO> getTherapistSchedules(Pageable pageable) {
        Page<TherapistSchedule> entities = therapistScheduleRepository.findAll(pageable);
        Page<TherapistScheduleDTO> dtoPage = entities.map(new Function<TherapistSchedule, TherapistScheduleDTO>() {

            @Override
            public TherapistScheduleDTO apply(TherapistSchedule therapistSchedule) {
                TherapistScheduleDTO dto = new TherapistScheduleDTO();
                dto.setId(therapistSchedule.getId());
                dto.setEndTime(therapistSchedule.getEndTime());
                dto.setStartTime(therapistSchedule.getStartTime());
                dto.setBooked(therapistSchedule.isBooked());
                dto.setAvailableDate(therapistSchedule.getAvailableDate());
                dto.setTherapistId(therapistSchedule.getTherapist() == null ? null : therapistSchedule.getTherapist().getId());

                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createTherapistSchedule(TherapistScheduleDTO dto) {
        therapistScheduleRepository.save(therapistScheduleMapper.toEntity(dto));
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
