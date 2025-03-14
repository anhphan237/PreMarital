package com.example.premarital.mappers;

import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.models.Role;

public interface RoleMapper {
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
    Role toEntityWithId(Long id, RoleDTO roleDTO);
}
