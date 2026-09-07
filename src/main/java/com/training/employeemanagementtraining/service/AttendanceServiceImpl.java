package com.training.employeemanagementtraining.service;



import com.training.employeemanagementtraining.dto.AttendanceRequest;
import com.training.employeemanagementtraining.dto.AttendanceResponse;
import com.training.employeemanagementtraining.entity.Attendance;
import com.training.employeemanagementtraining.repository.AttendanceRepository;
import com.training.employeemanagementtraining.service.AttendanceService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceResponse markAttendance(
            AttendanceRequest request) {

        Attendance attendance = Attendance.builder()
                .employeeId(request.getEmployeeId())
                .date(request.getDate())
                .status(request.getStatus())
                .checkInTime(request.getCheckInTime())
                .checkOutTime(request.getCheckOutTime())
                .remarks(request.getRemarks())
                .build();

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return mapToResponse(savedAttendance);
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AttendanceResponse getAttendanceById(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found with id: " + id
                        ));

        return mapToResponse(attendance);
    }

    @Override
    public AttendanceResponse updateAttendance(
            Long id,
            AttendanceRequest request) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found with id: " + id
                        ));

        attendance.setEmployeeId(request.getEmployeeId());
        attendance.setDate(request.getDate());
        attendance.setStatus(request.getStatus());
        attendance.setCheckInTime(request.getCheckInTime());
        attendance.setCheckOutTime(request.getCheckOutTime());
        attendance.setRemarks(request.getRemarks());

        Attendance updatedAttendance =
                attendanceRepository.save(attendance);

        return mapToResponse(updatedAttendance);
    }

    @Override
    public String deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found with id: " + id
                        ));

        attendanceRepository.delete(attendance);
        return "Deleted Successfully";
    }

    @Override
    public List<AttendanceResponse> getAttendanceByEmployee(
            Long employeeId) {

        return attendanceRepository
                .findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAttendanceByDate(
            LocalDate date) {

        return attendanceRepository
                .findByDate(date)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AttendanceResponse mapToResponse(
            Attendance attendance) {

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .employeeId(attendance.getEmployeeId())
                .date(attendance.getDate())
                .status(attendance.getStatus())
                .checkInTime(attendance.getCheckInTime())
                .checkOutTime(attendance.getCheckOutTime())
                .remarks(attendance.getRemarks())
                .build();
    }
}
