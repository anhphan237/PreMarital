package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.mappers.TherapistMajorMapper;
import com.example.premarital.models.TherapistMajor;
import org.springframework.stereotype.Component;

@Component
public class TherapistMajorMapperImpl implements TherapistMajorMapper {

    @Override
    public TherapistMajorDTO toDTO(TherapistMajor therapistMajor) {
        TherapistMajorDTO dto = new TherapistMajorDTO();
        dto.setId(therapistMajor.getId());
        dto.setName(therapistMajor.getName());
        dto.setIsActive(therapistMajor.getIsActive());
        return dto;
    }

    @Override
    public TherapistMajor toEntity(TherapistMajorDTO dto) {
        TherapistMajor entity = new TherapistMajor();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }

    @Override
    public TherapistMajor toEntityWithId(Long id, TherapistMajorDTO dto) {
        TherapistMajor entity = new TherapistMajor();
        entity.setId(id);
        entity.setName(dto.getName());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }
}
