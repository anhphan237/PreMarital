package com.example.premarital.services.impl;

import com.example.premarital.repositories.RoleRepository;
import com.example.premarital.dtos.UserDTO;
import com.example.premarital.mappers.UserMapper;
import com.example.premarital.models.User;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.function.Function;

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
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> entities = userRepository.findAll(pageable);
        Page<UserDTO> dtoPage = entities.map(new Function<User, UserDTO>() {
            @Override
            public UserDTO apply(User entity) {
                UserDTO dto = userMapper.toDTO(entity);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createUser(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.existsById(id) ? userMapper.toDTO(userRepository.findById(id).get()) : null;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userRepository.findById(id).map(user -> {
            user.setIsActive(false);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateUser(Long id, UserDTO updatedUserDTO) {
        User user = userRepository.findById(id).get();
        return userRepository.findById(id).map(existingUser -> {
            User updatedUser = userMapper.toEntityWithId(id, updatedUserDTO);
            updatedUser.setPassword(existingUser.getPassword());
            userRepository.save(updatedUser);
            return true;
        }).orElse(false);
    }
}
