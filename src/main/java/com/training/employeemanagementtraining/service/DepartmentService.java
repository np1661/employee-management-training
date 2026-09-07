package com.training.employeemanagementtraining.service;

import com.training.employeemanagementtraining.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(Long id);
    DepartmentDTO updateDepartment(Long id,DepartmentDTO departmentDTO);
    void deleteDepartment(Long id);
    List<DepartmentDTO> searchDepartments(String departmentName);
}
