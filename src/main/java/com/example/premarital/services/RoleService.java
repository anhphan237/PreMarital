package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.models.Role;

public interface RoleService {
    PagingResult<RoleDTO> getRoles(PaginationRequest request);
    void createRole(Role role);
    Role getRoleById(Long id);
    boolean deleteRoleById(Long id);
    boolean updateRole(Long id, Role updatedRole);
}
