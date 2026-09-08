package com.training.employeemanagementtraining.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "enter valid email")
    private String email;

    @NotBlank(message = "password is required")
    private String password;
}
