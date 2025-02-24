package com.example.premarital.controllers;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.UserDTO;
import com.example.premarital.services.UserService;
import com.example.premarital.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PagingResult<UserDTO>> getUsers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Sort.Direction direction
    ) {
        final PaginationRequest request = new PaginationRequest(page - 1, size, sortField, direction);
        final PagingResult<UserDTO> users = userService.getUsers(request);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO user){
        userService.createUser(user);
        return new ResponseEntity<>("User created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, user != null ? org.springframework.http.HttpStatus.OK : org.springframework.http.HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        return deleted
                ? ResponseEntity.ok("User deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        boolean updated = userService.updateUser(id, updatedUserDTO);
        return updated
                ? ResponseEntity.ok("User updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
