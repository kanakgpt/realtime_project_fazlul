package com.example.emp_managment.mapper;

import com.example.emp_managment.dto.EmployeeRequest;
import com.example.emp_managment.dto.EmployeeResponse;
import com.example.emp_managment.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        return new Employee(request.name(), request.email(), request.department());
    }

    public void updateEntity(Employee employee, EmployeeRequest request) {
        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setDepartment(request.department());
    }

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment());
    }
}
