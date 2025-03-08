package com.example.premarital.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
    private String username;

    @NotNull(message = "Role ID cannot be null")
    @Min(value = 1, message = "Role ID must be greater than 0")
    private Long roleId;

    @Size(max = 100, message = "Street must not exceed 100 characters")
    private String street;

    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @Size(max = 50, message = "State must not exceed 50 characters")
    private String state;

    @Pattern(regexp = "^[0-9]{5,10}$", message = "Invalid postal code format")
    private String postalCode;

    @Size(max = 60, message = "Country must not exceed 60 characters")
    private String country;

    @NotNull(message = "Account status cannot be null")
    private Boolean isActive;
}
