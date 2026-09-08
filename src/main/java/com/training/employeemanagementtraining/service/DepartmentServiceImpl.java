package com.training.employeemanagementtraining.service;

import com.training.employeemanagementtraining.dto.DepartmentDTO;
import com.training.employeemanagementtraining.entity.Department;
import com.training.employeemanagementtraining.repository.DepartmentRepository;
import com.training.employeemanagementtraining.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        Department department = new Department();

        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDescription(departmentDTO.getDescription());

        Department savedDepartment = departmentRepository.save(department);

        return convertToDTO(savedDepartment);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: " + id));

        return convertToDTO(department);
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: " + id));

        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDescription(departmentDTO.getDescription());

        Department updatedDepartment = departmentRepository.save(department);

        return convertToDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department=departmentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("department not found"));
        long employeeCount = employeeRepository.countByDepartment_Id(id);
        if (employeeCount > 0) {
            throw new RuntimeException("Cannot delete department because it has "
                    + employeeCount + " employee(s) assigned to it");
        }
        departmentRepository.delete(department);
    }

    @Override
    public List<DepartmentDTO> searchDepartments(String departmentName) {

        return departmentRepository.findByDepartmentNameContainingIgnoreCase(departmentName)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto=new DepartmentDTO();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDescription(department.getDescription());
        return dto;
    }
}
