package com.example.emp_managment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be at most 100 characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 254, message = "Email must be at most 254 characters")
        String email,

        @NotBlank(message = "Department is required")
        @Size(max = 100, message = "Department must be at most 100 characters")
        String department) {
}
