package com.example.premarital.user.mapper;

import com.example.premarital.user.dto.UserDTO;
import com.example.premarital.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.id", target = "roleId") // Map từ Role entity sang roleId
    UserDTO toDTO(User user);

    @Mapping(source = "roleId", target = "role.id") // Khi map từ DTO sang User
    User toEntity(UserDTO dto);
}
