package com.example.premarital.services.impl;

import com.example.premarital.exceptions.DuplicateUserException;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.exceptions.UserNotFoundException;
import com.example.premarital.repositories.RoleRepository;
import com.example.premarital.dtos.UserDTO;
import com.example.premarital.mappers.UserMapper;
import com.example.premarital.models.User;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        if (users.isEmpty()) {
            logger.warn("No users found in the system");
        }
        return users.map(userMapper::toDTO);
    }

    @Override
    public void createUser(UserDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new InvalidDataException("Email cannot be empty");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateUserException("Email " + dto.getEmail() + " is already in use");
        }

        try {
            User user = userMapper.toEntity(dto);
            userRepository.save(user);
            logger.info("User created successfully with ID: {}", user.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating user: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid user data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating user: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create user", e);
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public boolean deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        if (!user.getIsActive()) {
            logger.warn("User with ID {} is already inactive", id);
            return false;
        }

        try {
            user.setIsActive(false);
            userRepository.save(user);
            logger.info("User with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating user with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate user", e);
        }
    }

    @Override
    public boolean updateUser(Long id, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        if (!existingUser.getEmail().equals(updatedUserDTO.getEmail()) && userRepository.existsByEmail(updatedUserDTO.getEmail())) {
            throw new DuplicateUserException("Email " + updatedUserDTO.getEmail() + " is already in use");
        }

        try {
            User updatedUser = userMapper.toEntityWithId(id, updatedUserDTO);
            updatedUser.setPassword(existingUser.getPassword());
            userRepository.save(updatedUser);
            logger.info("User with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating user with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating user with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update user", e);
        }
    }
}
