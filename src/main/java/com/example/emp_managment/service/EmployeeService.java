package com.example.emp_managment.service;

import com.example.emp_managment.dto.EmployeeRequest;
import com.example.emp_managment.dto.EmployeeResponse;
import com.example.emp_managment.mapper.EmployeeMapper;
import com.example.emp_managment.model.Employee;
import com.example.emp_managment.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
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
        Employee employee = findEmployee(id);
        employeeMapper.updateEntity(employee, request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.delete(findEmployee(id));
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found: " + id));
    }
}
