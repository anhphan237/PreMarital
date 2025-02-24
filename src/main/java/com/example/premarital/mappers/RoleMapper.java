package com.example.premarital.mappers;

import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "id", target = "id") // Map id từ Role sang RoleDTO
    @Mapping(source = "name", target = "name") // Map name từ Role sang RoleDTO
    RoleDTO toDTO(Role role);

    @Mapping(source = "id", target = "id") // Map id từ RoleDTO sang Role
    @Mapping(source = "name", target = "name") // Map name từ RoleDTO sang Role
    Role toEntity(RoleDTO roleDTO);
}
