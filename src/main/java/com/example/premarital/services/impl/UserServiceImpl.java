package com.example.premarital.services.impl;

import com.example.premarital.models.Role;
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
                UserDTO dto = new UserDTO();
                // Conversion logic
                dto.setId(entity.getId());
                dto.setFirstName(entity.getFirstName());
                dto.setLastName(entity.getLastName());
                dto.setEmail(entity.getEmail());
                dto.setRoleId(entity.getRole().getId());
                dto.setUsername(entity.getUsername());
                dto.setCity(entity.getCity());
                dto.setCountry(entity.getCountry());
                dto.setPostalCode(entity.getPostalCode());
                dto.setState(entity.getState());
                dto.setRoleId(entity.getRole().getId());
                dto.setStreet(entity.getStreet());

                return dto;
            }
        });
        return dtoPage;
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

            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }
}
