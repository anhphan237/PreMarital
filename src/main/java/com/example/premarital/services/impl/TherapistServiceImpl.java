package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.mappers.TherapistMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.repositories.TherapistMajorRepository;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.TherapistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TherapistServiceImpl implements TherapistService {
    private final TherapistRepository therapistRepository;
    private final TherapistMapper therapistMapper;
    private final UserRepository userRepository;
    private final TherapistMajorRepository therapistMajorRepository;

    public TherapistServiceImpl(TherapistRepository therapistRepository, TherapistMapper therapistMapper, UserRepository userRepository, TherapistMajorRepository therapistMajorRepository) {
        this.therapistRepository = therapistRepository;
        this.therapistMapper = therapistMapper;
        this.userRepository = userRepository;
        this.therapistMajorRepository = therapistMajorRepository;
    }

    @Override
    public Page<TherapistDTO> getTherapists(Pageable pageable) {
        Page<Therapist> entities = therapistRepository.findAll(pageable);
        Page<TherapistDTO> dtoPage = entities.map(new Function<Therapist, TherapistDTO>() {

            @Override
            public TherapistDTO apply(Therapist therapist) {
                TherapistDTO dto = therapistMapper.toDTO(therapist);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public TherapistDTO createTherapist(TherapistDTO dto) {
        Therapist therapist = therapistMapper.toEntity(dto);
        therapistRepository.save(therapist);
        return therapistMapper.toDTO(therapist);
    }

    @Override
    public TherapistDTO getTherapistById(Long id) {
        return therapistMapper.toDTO(therapistRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteTherapistById(Long id) {
        return therapistRepository.findById(id).map(therapist -> {
            therapist.setIsActive(false);
            therapistRepository.save(therapist);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateTherapist(Long id, TherapistDTO updatedTherapistDTO) {
        Therapist existingTherapist = therapistRepository.findById(id).orElse(null);
        if (existingTherapist != null) {
            existingTherapist.setId(id);
            existingTherapist.setBio(updatedTherapistDTO.getBio());
            existingTherapist.setUser(userRepository.findById(id).orElse(null));
            existingTherapist.setTherapistMajor(therapistMajorRepository.findById(updatedTherapistDTO.getTherapistMajorId()).orElse(null));
            existingTherapist.setIsActive(updatedTherapistDTO.getIsActive());
            existingTherapist.setCertificationExpirationDate(updatedTherapistDTO.getCertificationExpirationDate());
            existingTherapist.setCertificationIssueDate(updatedTherapistDTO.getCertificationIssueDate());
            existingTherapist.setCertificationIssuedBy(updatedTherapistDTO.getCertificationIssuedBy());
            existingTherapist.setTherapistCertificationName(updatedTherapistDTO.getTherapistCertificationName());
            existingTherapist.setIsActive(updatedTherapistDTO.getIsActive());
            therapistRepository.save(existingTherapist);
            return true;
        }else {
            return false;
        }
    }
}
