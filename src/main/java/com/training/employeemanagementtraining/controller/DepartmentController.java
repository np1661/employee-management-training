package com.training.employeemanagementtraining.controller;

import com.training.employeemanagementtraining.dto.DepartmentDTO;
import com.training.employeemanagementtraining.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(
            @Valid @RequestBody DepartmentDTO departmentDTO) {

        return new ResponseEntity<>(
                departmentService.createDepartment(departmentDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentDTO departmentDTO) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, departmentDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Department deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<DepartmentDTO>> searchDepartments(
            @RequestParam String departmentName) {

        return ResponseEntity.ok(
                departmentService.searchDepartments(departmentName)
        );
    }

}
