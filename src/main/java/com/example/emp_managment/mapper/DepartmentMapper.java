package com.example.emp_managment.mapper;

import com.example.emp_managment.dto.DepartmentDTO.CreateDepartmentRequest;
import com.example.emp_managment.dto.DepartmentDTO.DepartmentResponse;
import com.example.emp_managment.dto.DepartmentDTO.UpdateDepartmentRequest;
import com.example.emp_managment.dto.EmployeeDTO.EmployeeResponse;
import com.example.emp_managment.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    private final EmployeeMapper employeeMapper;

    public Department toEntity(CreateDepartmentRequest request) {
        Department department = new Department();
        department.setName(request.name());
        return department;
    }

    public void updateEntity(Department department, UpdateDepartmentRequest request) {
        department.setName(request.name());
    }

    public DepartmentResponse toResponse(Department department) {
        List<EmployeeResponse> employees = department.getEmployees().stream()
                .map(employeeMapper::toResponse)
                .toList();

        return new DepartmentResponse(department.getId(), department.getName(), employees);
    }
}