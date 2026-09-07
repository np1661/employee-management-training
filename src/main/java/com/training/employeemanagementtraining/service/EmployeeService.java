package com.training.employeemanagementtraining.service;

import com.training.employeemanagementtraining.dto.EmployeeRequest;
import com.training.employeemanagementtraining.dto.EmployeeResponse;
import com.training.employeemanagementtraining.entity.Employee;
import com.training.employeemanagementtraining.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public String createEmployee(EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setAddress(request.getAddress());
        employee.setDepartment(request.getDepartment());
        employeeRepository.save(employee);
        return "Employee created successfully";
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Employee not found with id " + id));

        return mapToResponse(employee);
    }

    public String updateEmployee(Long id, EmployeeRequest update) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Employee not found with id " + id));

        employee.setName(update.getName());
        employee.setEmail(update.getEmail());
        employee.setAddress(update.getAddress());
        employee.setDepartment(update.getDepartment());
        employeeRepository.save(employee);
        return "Employee updated successfully";
    }

    public String deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Employee not found with id " + id));

        employeeRepository.delete(employee);

        return "Employee deleted successfully";
    }

    public List<EmployeeResponse> searchEmployees(String name) {

        return employeeRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getDepartment()
        );
    }

}
