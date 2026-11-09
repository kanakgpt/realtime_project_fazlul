package com.example.emp_managment.dto.EmployeeDTO;

import com.example.emp_managment.model.Department;

public record EmployeeResponse(Long id, String name, String email, Long departmentId) {}