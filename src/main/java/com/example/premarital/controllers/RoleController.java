package com.example.premarital.controllers;

import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.models.Role;
import com.example.premarital.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ){
        if (page < 1 || size <= 1) {
            return ResponseEntity.badRequest().body("Page number must be >= 1 and size must be > 1");
        }

        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<RoleDTO> roles = roleService.getRoles(pageable);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createRole(@Valid @RequestBody RoleDTO role){
        roleService.createRole(role);
        return new ResponseEntity<>("Role created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findRoleById(@PathVariable Long id){
        RoleDTO role = roleService.getRoleById(id);
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
    public ResponseEntity<String> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDTO updatedRole) {
        boolean updated = roleService.updateRole(id, updatedRole);
        return updated
                ? ResponseEntity.ok("Role updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
    }
}
