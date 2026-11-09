package com.example.emp_managment.service;

import com.example.emp_managment.dto.EmployeeDTO.EmployeeRequest;
import com.example.emp_managment.dto.EmployeeDTO.EmployeeResponse;
import com.example.emp_managment.exception.ResourceNotFoundException;
import com.example.emp_managment.mapper.EmployeeMapper;
import com.example.emp_managment.model.Department;
import com.example.emp_managment.model.Employee;
import com.example.emp_managment.repository.DepartmentRepository;
import com.example.emp_managment.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Department department = findDepartment(request.departmentId());
        Employee saved = employeeRepository.save(employeeMapper.toEntity(request, department));
        return employeeMapper.toResponse(saved);
    }

    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    public EmployeeResponse getEmployee(Long id) {
        return employeeMapper.toResponse(findEmployee(id));
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        Department department = findDepartment(request.departmentId());
        employeeMapper.updateEntity(employee, request, department);
        return employeeMapper.toResponse(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.delete(findEmployee(id));
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found: " + id));
    }
    private Department findDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id " + departmentId));
    }
}
