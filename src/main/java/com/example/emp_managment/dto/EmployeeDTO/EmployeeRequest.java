package com.example.emp_managment.dto.EmployeeDTO;

import com.example.emp_managment.model.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(String name, String email, Long departmentId) {}