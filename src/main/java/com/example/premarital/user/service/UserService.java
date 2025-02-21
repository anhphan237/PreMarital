package com.example.premarital.user.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.user.dto.UserDTO;
import com.example.premarital.user.model.User;

public interface UserService {
    PagingResult<UserDTO> getUsers(PaginationRequest request);
    UserDTO createUser(UserDTO dto);
    User getUserById(Long id);
    boolean deleteUserById(Long id);
    boolean updateUser(Long id, UserDTO updatedUserDTO);
}
