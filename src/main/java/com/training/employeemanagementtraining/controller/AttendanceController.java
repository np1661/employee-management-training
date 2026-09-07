package com.training.employeemanagementtraining.controller;


import com.training.employeemanagementtraining.dto.AttendanceRequest;
import com.training.employeemanagementtraining.dto.AttendanceResponse;
import com.training.employeemanagementtraining.service.AttendanceService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // POST /api/attendance
    @PostMapping
    public ResponseEntity<AttendanceResponse> markAttendance(
            @RequestBody AttendanceRequest request) {

        AttendanceResponse response =
                attendanceService.markAttendance(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET /api/attendance
    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance()
        );
    }

    // GET /api/attendance/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(id)
        );
    }

    // PUT /api/attendance/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceRequest request) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(id, request)
        );
    }

    // DELETE /api/attendance/{id}
    @DeleteMapping("/{id}")
    public String deleteAttendance(
            @PathVariable Long id) {

        return attendanceService.deleteAttendance(id);
    }

    // GET /api/attendance/employee/{employeeId}
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceResponse>>
    getAttendanceByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                attendanceService
                        .getAttendanceByEmployee(employeeId)
        );
    }

    // GET /api/attendance/date/{date}
    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceResponse>>
    getAttendanceByDate(
            @PathVariable LocalDate date) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByDate(date)
        );
    }
}
