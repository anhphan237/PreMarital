package com.example.premarital.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long id;

    @NotBlank(message = "Role name cannot be blank")
    @Size(min = 3, max = 20, message = "Role name must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Role status cannot be null")
    private Boolean isActive;
}
