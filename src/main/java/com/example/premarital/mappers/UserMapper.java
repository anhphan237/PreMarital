package com.example.premarital.mappers;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
