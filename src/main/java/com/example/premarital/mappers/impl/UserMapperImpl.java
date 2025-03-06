package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.mappers.UserMapper;
import com.example.premarital.models.Role;
import com.example.premarital.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        if ( user == null ) {
            userDTO = null;
        } else {
            userDTO.setEmail( user.getEmail());
            userDTO.setRoleId( user.getRole().getId());
            userDTO.setId( user.getId());
            userDTO.setFirstName( user.getFirstName());
            userDTO.setCity( user.getCity());
            userDTO.setStreet( user.getStreet());
            userDTO.setPostalCode( user.getPostalCode());
            userDTO.setLastName( user.getLastName() );
            userDTO.setCountry( user.getCountry());
            userDTO.setState( user.getState());
            userDTO.setUsername( user.getUsername());
            userDTO.setIsActive( user.getIsActive());
        }
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setCity(dto.getCity());
        user.setStreet(dto.getStreet());
        user.setPostalCode(dto.getPostalCode());
        user.setCountry(dto.getCountry());
        user.setState(dto.getState());
        user.setIsActive(dto.getIsActive());

        // Nếu cần ánh xạ Role từ roleId
        if (dto.getRoleId() != null) {
            Role role = new Role();
            role.setId(dto.getRoleId());
            user.setRole(role);
        }

        return user;
    }
}
