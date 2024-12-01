package com.spring.jwt.controller;


import com.spring.jwt.entity.EmployeeAttendance;
import com.spring.jwt.service.AttendanceService;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/EA")
@RequiredArgsConstructor
public class EmployeeAttandance {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping("/attandances/save")
    public ResponseEntity<?> addAttendance(@RequestBody EmployeeAttendance attendance) {
        try {

            EmployeeAttendance savedAttendance = attendanceService.addAttendance(attendance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }
    @GetMapping("/attendance/{employeeId}")
    public ResponseEntity<?> getAttendanceByEmployeeId(@PathVariable Integer employeeId,@PathVariable Integer referenceId) {
        try {
            List<EmployeeAttendance> attendances = attendanceService.getAttendanceByEmployeeId(employeeId,referenceId);
            return ResponseEntity.ok(attendances);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }
    @GetMapping("/attendance/{employeeId}/{date}")
    public ResponseEntity<?> getAttendanceByEmployeeIdAndDate(
            @PathVariable Integer employeeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            ,@PathVariable Integer referenceId) {
        try {
            EmployeeAttendance attendance = attendanceService.getAttendanceByEmployeeIdAndDate(employeeId, date,referenceId);
            return ResponseEntity.ok(attendance);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }

    @DeleteMapping("/attendance/{employeeId}/{date}")
    public ResponseEntity<?> deleteAttendanceByEmployeeIdAndDate(
            @PathVariable Integer employeeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            ,@PathVariable Integer referenceId) {
        try {
            attendanceService.deleteAttendanceByEmployeeIdAndDate(employeeId, date,referenceId);
            return ResponseEntity.ok(new BaseResponseDTO("Successful", "Attendance record deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }

    @PatchMapping("/attendance/{employeeId}/{date}")
    public ResponseEntity<?> updateAttendanceStatusByEmployeeId(
            @PathVariable Integer employeeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String status
            ,@PathVariable Integer referenceId) {
        try {
            EmployeeAttendance updatedAttendance = attendanceService.updateAttendanceStatusByEmployeeId(employeeId, date, status,referenceId);
            return ResponseEntity.ok(updatedAttendance);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }



}
