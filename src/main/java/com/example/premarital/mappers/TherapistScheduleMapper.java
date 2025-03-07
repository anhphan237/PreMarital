package com.example.premarital.mappers;

import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.models.TherapistSchedule;

public interface TherapistScheduleMapper {
    TherapistScheduleDTO toDTO(TherapistSchedule entity);
    TherapistSchedule toEntity(TherapistScheduleDTO dto);
    TherapistSchedule toEntityWithId(Long id, TherapistScheduleDTO dto);
}
