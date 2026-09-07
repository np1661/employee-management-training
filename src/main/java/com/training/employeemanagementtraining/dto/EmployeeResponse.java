package com.training.employeemanagementtraining.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String department;

}
