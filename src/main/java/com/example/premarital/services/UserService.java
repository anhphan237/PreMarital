package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.UserDTO;
import com.example.premarital.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    //PagingResult<UserDTO> getUsers(PaginationRequest request);
    Page<UserDTO> getUsers(Pageable pageable);
    UserDTO createUser(UserDTO dto);
    User getUserById(Long id);
    boolean deleteUserById(Long id);
    boolean updateUser(Long id, UserDTO updatedUserDTO);
}
