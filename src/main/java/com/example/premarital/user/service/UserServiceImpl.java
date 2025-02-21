package com.example.premarital.user.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PaginationUtils;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.role.model.Role;
import com.example.premarital.role.repository.RoleRepository;
import com.example.premarital.user.dto.UserDTO;
import com.example.premarital.user.mapper.UserMapper;
import com.example.premarital.user.model.User;
import com.example.premarital.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public PagingResult<UserDTO> getUsers(PaginationRequest request) {
        final Pageable pageable = PaginationUtils.getPageable(request);
        final Page<User> entities = userRepository.findAll(pageable);
        final List<UserDTO> entitiesDto = entities.stream().map(userMapper::toDTO).toList();
        return new PagingResult<>(
                entitiesDto,
                entities.getTotalPages(),
                entities.getTotalElements(),
                entities.getSize(),
                entities.getNumber(),
                entities.isEmpty()
        );
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role does not exist!"));

        User user = userMapper.toEntity(dto);
        user.setRole(role);
        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.existsById(id) ? userRepository.findById(id).get() : null;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateUser(Long id, UserDTO updatedUserDTO) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstName(updatedUserDTO.getFirstName());
            existingUser.setLastName(updatedUserDTO.getLastName());
            existingUser.setEmail(updatedUserDTO.getEmail());
            existingUser.setRole(roleRepository.findById(updatedUserDTO.getRoleId()).get());
            existingUser.setUsername(updatedUserDTO.getUsername());
            existingUser.setPassword(updatedUserDTO.getPassword());

            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }
}
