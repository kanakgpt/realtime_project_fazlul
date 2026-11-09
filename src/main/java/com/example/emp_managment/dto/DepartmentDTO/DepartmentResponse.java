package com.example.emp_managment.dto.DepartmentDTO;

import com.example.emp_managment.dto.EmployeeDTO.EmployeeResponse;

import java.util.List;

public record DepartmentResponse(Long id, String name, List<EmployeeResponse> employees) {}