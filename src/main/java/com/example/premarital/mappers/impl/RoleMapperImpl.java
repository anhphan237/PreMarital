package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.mappers.RoleMapper;
import com.example.premarital.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setIsActive(role.getIsActive());
        return roleDTO;
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setIsActive(roleDTO.getIsActive());
        return role;
    }
}
