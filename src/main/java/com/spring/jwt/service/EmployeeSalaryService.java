package com.spring.jwt.service;

import com.spring.jwt.dto.SalaryBreakdownDto;
import com.spring.jwt.dto.SalaryDto;
import com.spring.jwt.entity.Employee;
import com.spring.jwt.entity.EmployeeAttendance;
import com.spring.jwt.repository.EmployeeAttendanceRepository;
import com.spring.jwt.repository.EmployeeRepository;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeSalaryService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeAttendanceRepository attendanceRepository;
    public Object getSalaryBreakDown(Integer employeeId, Integer referenceId, Integer month) {

        Integer totalSalaryAsterBreakdown = 0;
        List<EmployeeAttendance> employeeAttendances = attendanceRepository.findByERMId(employeeId,referenceId,month);
        Employee employee = employeeRepository.findByIdAndReferenceId(employeeId,referenceId)
                .orElseThrow(()->new RuntimeException("employee not found"));
        Integer totalSalary = Integer.parseInt(employee.getSalary()) ;
        Integer perDaySalary = Integer.parseInt(employee.getSalary()) / 30 ;
        Integer halfDaySalary = (int) (perDaySalary * 0.50);



        if (employeeAttendances.size() == 0)throw new RuntimeException("Employee Attendance not found");

        //PRESENT
        //ANSENT
        //HALFDAY
        List<SalaryBreakdownDto> salaryBreakdownDtos = new LinkedList<>();

        for (EmployeeAttendance employeeAttendance : employeeAttendances){
            SalaryBreakdownDto salaryBreakdownDto = new SalaryBreakdownDto();
            salaryBreakdownDto.setDate(employeeAttendance.getDate());
            salaryBreakdownDto.setStatus(employeeAttendance.getStatus());
            salaryBreakdownDtos.add(salaryBreakdownDto);
            if (employeeAttendance.getStatus().equals("PRESENT")){
                totalSalaryAsterBreakdown = totalSalaryAsterBreakdown+perDaySalary;
            }else if (employeeAttendance.getStatus().equals("HALFDAY")){
                totalSalaryAsterBreakdown = totalSalaryAsterBreakdown+(perDaySalary/50);

            }
        }
        return new SalaryDto(employeeId,halfDaySalary,perDaySalary,totalSalary,totalSalaryAsterBreakdown,salaryBreakdownDtos);

    }
}
