package com.example.emp_managment.mapper;

import com.example.emp_managment.dto.EmployeeDTO.EmployeeRequest;
import com.example.emp_managment.dto.EmployeeDTO.EmployeeResponse;
import com.example.emp_managment.model.Department;
import com.example.emp_managment.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request, Department department) {
        return new Employee(request.name(), request.email(), department);
    }

    public void updateEntity(Employee employee, EmployeeRequest request, Department department) {
        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setDepartment(department);
    }

    public EmployeeResponse toResponse(Employee employee) {
        Long departmentId = employee.getDepartment() != null
                ? employee.getDepartment().getId()
                : null;

        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                departmentId);
    }
}