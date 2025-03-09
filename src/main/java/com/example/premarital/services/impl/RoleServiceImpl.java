package com.example.premarital.services.impl;

import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.mappers.RoleMapper;
import com.example.premarital.models.Role;
import com.example.premarital.repositories.RoleRepository;
import com.example.premarital.services.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Page<RoleDTO> getRoles(Pageable pageable) {
        Page<Role> entities = roleRepository.findAll(pageable);
        Page<RoleDTO> dtoPage = entities.map(new Function<Role, RoleDTO>() {

            @Override
            public RoleDTO apply(Role role) {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setId(role.getId());
                roleDTO.setName(role.getName());
                return roleDTO;
            }
        });
        return dtoPage;
    }

    @Override
    public void createRole(RoleDTO role) {
        roleRepository.save(roleMapper.toEntity(role));
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        return roleMapper.toDTO(roleRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteRoleById(Long id) {
        return roleRepository.findById(id).map(role -> {
            if(!role.getUsers().isEmpty()){
                throw new IllegalStateException("Cannot delete role with id " + role.getName() + " as it is in use");
            }
            role.setIsActive(false);
            roleRepository.save(role);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateRole(Long id, RoleDTO updatedRoleDTO) {
        return roleRepository.findById(id).map(existingRole -> {
            Role updatedRole = roleMapper.toEntityWithId(id, updatedRoleDTO);
            roleRepository.save(updatedRole);
            return true;
        }).orElse(false);
    }
}
