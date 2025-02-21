package com.example.premarital.role.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.role.dto.RoleDTO;
import com.example.premarital.role.model.Role;

public interface RoleService {
    PagingResult<RoleDTO> getRoles(PaginationRequest request);
    void createRole(Role role);
    Role getRoleById(Long id);
    boolean deleteRoleById(Long id);
    boolean updateRole(Long id, Role updatedRole);
}
