package com.training.employeemanagementtraining.service;


import com.training.employeemanagementtraining.dto.AttendanceRequest;
import com.training.employeemanagementtraining.dto.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceResponse markAttendance(AttendanceRequest request);

    List<AttendanceResponse> getAllAttendance();

    AttendanceResponse getAttendanceById(Long id);

    AttendanceResponse updateAttendance(
            Long id,
            AttendanceRequest request
    );

    String deleteAttendance(Long id);

    List<AttendanceResponse> getAttendanceByEmployee(
            Long employeeId
    );

    List<AttendanceResponse> getAttendanceByDate(
            LocalDate date
    );
}
