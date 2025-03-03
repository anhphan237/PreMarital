package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.mappers.TherapistMajorMapper;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.repositories.TherapistRepository;
import org.springframework.stereotype.Component;

@Component
public class TherapistMajorMapperImpl implements TherapistMajorMapper {
    private final TherapistRepository therapistRepository;

    public TherapistMajorMapperImpl(TherapistRepository therapistRepository) {
        this.therapistRepository = therapistRepository;
    }

    @Override
    public TherapistMajorDTO toDTO(TherapistMajor therapistMajor) {
        TherapistMajorDTO dto = new TherapistMajorDTO();
        dto.setId(therapistMajor.getId());
        dto.setName(therapistMajor.getName());
        return dto;
    }

    @Override
    public TherapistMajor toEntity(TherapistMajorDTO dto) {
        TherapistMajor entity = new TherapistMajor();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
