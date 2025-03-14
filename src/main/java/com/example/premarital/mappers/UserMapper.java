package com.example.premarital.mappers;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.models.User;

public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
    User toEntityWithId(Long id, UserDTO dto);
}
