package com.example.emp_managment.service;

import com.example.emp_managment.dto.DepartmentDTO.CreateDepartmentRequest;
import com.example.emp_managment.dto.DepartmentDTO.DepartmentResponse;
import com.example.emp_managment.dto.DepartmentDTO.UpdateDepartmentRequest;
import com.example.emp_managment.exception.ResourceNotFoundException;
import com.example.emp_managment.mapper.DepartmentMapper;
import com.example.emp_managment.model.Department;
import com.example.emp_managment.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;


    public DepartmentResponse create(CreateDepartmentRequest request) {
        Department department = departmentMapper.toEntity(request);
        Department saved = departmentRepository.save(department);
        return departmentMapper.toResponse(saved);
    }


    public List<DepartmentResponse> getAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .toList();
    }


    public DepartmentResponse getById(Long id) {
        return departmentMapper.toResponse(findOrThrow(id));
    }


    public DepartmentResponse update(Long id, UpdateDepartmentRequest request) {
        Department department = findOrThrow(id);
        departmentMapper.updateEntity(department, request);
        return departmentMapper.toResponse(department);
    }


    public void delete(Long id) {
        departmentRepository.delete(findOrThrow(id));
    }

    private Department findOrThrow(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
    }
}