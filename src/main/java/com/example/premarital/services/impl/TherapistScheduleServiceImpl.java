package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.mappers.TherapistScheduleMapper;
import com.example.premarital.models.TherapistSchedule;
import com.example.premarital.repositories.TherapistScheduleRepository;
import com.example.premarital.services.TherapistScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TherapistScheduleServiceImpl implements TherapistScheduleService {
    private final TherapistScheduleRepository therapistScheduleRepository;
    private final TherapistScheduleMapper therapistScheduleMapper;

    public TherapistScheduleServiceImpl(TherapistScheduleRepository therapistScheduleRepository, TherapistScheduleMapper therapistScheduleMapper) {
        this.therapistScheduleRepository = therapistScheduleRepository;
        this.therapistScheduleMapper = therapistScheduleMapper;
    }

    @Override
    public Page<TherapistScheduleDTO> getTherapistSchedules(Pageable pageable) {
        Page<TherapistSchedule> entities = therapistScheduleRepository.findAll(pageable);
        Page<TherapistScheduleDTO> dtoPage = entities.map(new Function<TherapistSchedule, TherapistScheduleDTO>() {

            @Override
            public TherapistScheduleDTO apply(TherapistSchedule therapistSchedule) {
                TherapistScheduleDTO dto = therapistScheduleMapper.toDTO(therapistSchedule);
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
    public TherapistScheduleDTO getTherapistScheduleById(Long id) {
        return therapistScheduleMapper.toDTO(therapistScheduleRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteTherapistScheduleById(Long id) {
        return therapistScheduleRepository.findById(id).map(therapistSchedule -> {
            therapistSchedule.setActive(false);
            therapistScheduleRepository.save(therapistSchedule);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateTherapistSchedule(Long id, TherapistScheduleDTO dto) {
        return therapistScheduleRepository.findById(id).map(therapistMajor -> {
            TherapistSchedule updatedTherapistSchedule = therapistScheduleMapper.toEntityWithId(id, dto);
            therapistScheduleRepository.save(updatedTherapistSchedule);
            return true;
        }).orElse(false);
    }
}
