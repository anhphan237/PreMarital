package com.example.premarital.mappers;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.models.TherapistMajor;

public interface TherapistMajorMapper {
    TherapistMajorDTO toDTO(TherapistMajor therapistMajor);
    TherapistMajor toEntity(TherapistMajorDTO dto);
    TherapistMajor toEntityWithId(Long id, TherapistMajorDTO dto);
}
