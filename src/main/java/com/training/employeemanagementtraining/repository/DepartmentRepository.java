package com.training.employeemanagementtraining.repository;

import com.training.employeemanagementtraining.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);
}
