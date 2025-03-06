package com.example.premarital.services;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> getUsers(Pageable pageable);
    void createUser(UserDTO dto);
    UserDTO getUserById(Long id);
    boolean deleteUserById(Long id);
    boolean updateUser(Long id, UserDTO updatedUserDTO);
}
