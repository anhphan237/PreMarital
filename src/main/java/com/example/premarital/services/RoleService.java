package com.example.premarital.services;

import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Page<RoleDTO> getRoles(Pageable pageable);
    void createRole(RoleDTO role);
    RoleDTO getRoleById(Long id);
    boolean deleteRoleById(Long id);
    boolean updateRole(Long id, RoleDTO updatedRole);
}
