package com.example.emp_managment.controller;

import com.example.emp_managment.dto.EmployeeRequest;
import com.example.emp_managment.dto.EmployeeResponse;
import com.example.emp_managment.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse savedEmployee = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateEmployee(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
