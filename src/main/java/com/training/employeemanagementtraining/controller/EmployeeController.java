package com.training.employeemanagementtraining.controller;

import com.training.employeemanagementtraining.dto.EmployeeRequest;
import com.training.employeemanagementtraining.dto.EmployeeResponse;
import com.training.employeemanagementtraining.entity.Employee;
import com.training.employeemanagementtraining.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/getby-id/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest update) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, update));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchEmployees(name));
    }


}
