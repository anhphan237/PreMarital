package com.example.premarital.controllers;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.models.Role;
import com.example.premarital.services.RoleService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<PagingResult<RoleDTO>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Sort.Direction direction
    ){
        final PaginationRequest request = new PaginationRequest(page - 1, size, sortField, direction);
        final PagingResult<RoleDTO> roles = roleService.getRoles(request);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody Role role){
        roleService.createRole(role);
        return new ResponseEntity<>("Role created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable Long id){
        Role role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, role != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        boolean deleted = roleService.deleteRoleById(id);
        return deleted
                ? ResponseEntity.ok("Role deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        boolean updated = roleService.updateRole(id, updatedRole);
        return updated
                ? ResponseEntity.ok("Role updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
    }
}
