package com.training.employeemanagementtraining.dto;


import com.training.employeemanagementtraining.entity.Attendance;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponse {

    private Long employeeId;

    private String employeeName;

    private String departmentName;

    private LocalDate date;

    private Attendance.AttendanceStatus status;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    private String remarks;
}
