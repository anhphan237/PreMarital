package com.example.premarital.services.impl;

import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.RoleMapper;
import com.example.premarital.models.Role;
import com.example.premarital.repositories.RoleRepository;
import com.example.premarital.services.RoleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public Page<RoleDTO> getRoles(Pageable pageable) {
        Page<Role> roles = roleRepository.findRolesByIsActiveTrue(pageable);
        if (roles.isEmpty()) {
            logger.warn("No roles found in the system");
        }
        return roles.map(roleMapper::toDTO);
    }

    @Override
    @Transactional
    public void createRole(RoleDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when creating a role");
        }

        try {
            Role role = roleMapper.toEntity(dto);
            roleRepository.save(role);
            logger.info("Role created successfully with ID: {}", role.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating role: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid role data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating role: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create role", e);
        }
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Role with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role with ID " + id + " not found"));

        if (!role.getUsers().isEmpty()) {
            throw new IllegalStateException("Cannot delete role '" + role.getName() + "' as it is in use");
        }

        try {
            role.setIsActive(false);
            roleRepository.save(role);
            logger.info("Role with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating role with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate role", e);
        }
    }

    @Override
    @Transactional
    public boolean updateRole(Long id, RoleDTO updatedRoleDTO) {
        if (updatedRoleDTO.getId() != null) {
            throw new InvalidDataException("ID must be null when updating a role");
        }

        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role with ID " + id + " not found");
        }

        try {
            Role updatedRole = roleMapper.toEntityWithId(id, updatedRoleDTO);
            roleRepository.save(updatedRole);
            logger.info("Role with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating role with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating role with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update role", e);
        }
    }
}
