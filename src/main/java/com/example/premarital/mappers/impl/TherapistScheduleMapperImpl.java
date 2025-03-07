package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.mappers.TherapistScheduleMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.TherapistSchedule;
import com.example.premarital.repositories.TherapistRepository;
import org.springframework.stereotype.Component;

@Component
public class TherapistScheduleMapperImpl implements TherapistScheduleMapper {
    private final TherapistRepository therapistRepository;

    public TherapistScheduleMapperImpl(TherapistRepository therapistRepository) {
        this.therapistRepository = therapistRepository;
    }

    @Override
    public TherapistScheduleDTO toDTO(TherapistSchedule entity) {
        TherapistScheduleDTO dto = new TherapistScheduleDTO();

        dto.setId(entity.getId());
        dto.setTherapistId(entity.getTherapist().getId());
        dto.setAvailableDate(entity.getAvailableDate());
        dto.setBooked(entity.isBooked());
        dto.setActive(entity.isActive());
        dto.setEndTime(entity.getEndTime());
        dto.setStartTime(entity.getStartTime());

        return dto;
    }

    @Override
    public TherapistSchedule toEntity(TherapistScheduleDTO dto) {
        TherapistSchedule entity = new TherapistSchedule();
        entity.setId(dto.getId());
        entity.setAvailableDate(dto.getAvailableDate());
        entity.setBooked(dto.isBooked());
        entity.setActive(dto.isActive());
        entity.setEndTime(dto.getEndTime());
        entity.setStartTime(dto.getStartTime());
        entity.setTherapist( therapistRepository.getReferenceById(dto.getTherapistId()) );
        return entity;
    }

    @Override
    public TherapistSchedule toEntityWithId(Long id, TherapistScheduleDTO dto) {
        TherapistSchedule entity = new TherapistSchedule();
        entity.setId(id);
        entity.setAvailableDate(dto.getAvailableDate());
        entity.setBooked(dto.isBooked());
        entity.setActive(dto.isActive());
        entity.setEndTime(dto.getEndTime());
        entity.setStartTime(dto.getStartTime());
        if (dto.getTherapistId() != null) {
            Therapist therapist = new Therapist();
            therapist.setId(dto.getTherapistId());
            entity.setTherapist(therapist);
        }
        return entity;
    }
}
