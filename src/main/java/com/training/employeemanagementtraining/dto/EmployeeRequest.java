package com.training.employeemanagementtraining.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {

    @NotBlank(message = "Please Enter the name")
    private String name;

    @NotBlank(message = "Please Enter the email")
    @Email(message = "Invalid Email formate")
    private String email;

    @NotBlank(message = "Please Enter the address")
    private String address;

    @NotBlank(message = "Please Enter the department")
    private String department;
}
