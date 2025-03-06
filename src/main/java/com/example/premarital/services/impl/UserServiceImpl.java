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

            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }
}
