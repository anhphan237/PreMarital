package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.mappers.UserMapper;
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
        }
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        return null;
    }
}
