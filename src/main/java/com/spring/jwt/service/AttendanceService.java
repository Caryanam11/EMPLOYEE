package com.spring.jwt.service;

import com.spring.jwt.entity.EmployeeAttendance;
import com.spring.jwt.repository.EmployeeAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private EmployeeAttendanceRepository attendanceRepository;

    public EmployeeAttendance addAttendance(EmployeeAttendance attendance) {
        attendance.setMonth(attendance.getDate().getMonthValue());
        return attendanceRepository.save(attendance);
    }

    public List<EmployeeAttendance> getAttendanceByEmployeeId(Integer employeeId,Integer referenceId) {
        return attendanceRepository.findByEmployeeId(employeeId,referenceId);
    }

    public EmployeeAttendance getAttendanceByEmployeeIdAndDate(Integer employeeId, LocalDate date,Integer referenceId) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date, referenceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }

    public void deleteAttendanceByEmployeeIdAndDate(Integer employeeId, LocalDate date,Integer referenceId) {
        attendanceRepository.deleteByEmployeeIdAndDate(employeeId, date, referenceId);
    }

    public EmployeeAttendance updateAttendanceStatusByEmployeeId(Integer employeeId,LocalDate date,String status,Integer referenceId) {
        EmployeeAttendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date, referenceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        attendance.setStatus(status);
        return attendanceRepository.save(attendance);
    }
}
