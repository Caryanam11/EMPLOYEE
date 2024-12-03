package com.spring.jwt.service;

import com.spring.jwt.dto.SalaryBreakdownDto;
import com.spring.jwt.dto.SalaryDto;
import com.spring.jwt.entity.Employee;
import com.spring.jwt.entity.EmployeeAttendance;
import com.spring.jwt.entity.SalaryPaidOrUnPaid;
import com.spring.jwt.repository.EmployeeAttendanceRepository;
import com.spring.jwt.repository.EmployeeRepository;
import com.spring.jwt.repository.SalaryPaidOrUnPaidRepo;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSalaryService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalaryPaidOrUnPaidRepo salaryPaidOrUnPaidRepo;
    @Autowired
    private EmployeeAttendanceRepository attendanceRepository;
    public Object getSalaryBreakDown(Integer employeeId, Integer referenceId, Integer month) {
        System.out.println(month);
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
                totalSalaryAsterBreakdown = totalSalaryAsterBreakdown+(halfDaySalary);

            }


        }
        Optional<SalaryPaidOrUnPaid> salaryPaidOrUnPaid = salaryPaidOrUnPaidRepo.findByMonthAndSalaryPaidOrUnPaidAndReferenceId(month,employeeId,referenceId);
    Boolean salPaidOrNOt = false;



    if (salaryPaidOrUnPaid.isPresent() ){
        salPaidOrNOt = true;

    }else if (!salPaidOrNOt){
        SalaryPaidOrUnPaid salaryPaidOrUnPaid1 = new SalaryPaidOrUnPaid();
        salaryPaidOrUnPaid1.setEmployeeId(employeeId);

        salaryPaidOrUnPaid1.setSalaryPaidOrUnPaid(salPaidOrNOt);

        salaryPaidOrUnPaid1.setReferenceId(referenceId);
        salaryPaidOrUnPaid1.setMonth(month);

        salaryPaidOrUnPaidRepo.save(salaryPaidOrUnPaid1);
    }
        return new SalaryDto(employeeId,halfDaySalary,perDaySalary,totalSalary,totalSalaryAsterBreakdown,salaryBreakdownDtos,salPaidOrNOt);

    }
    public String updatedSalaryPaidOrUnpaid(Integer employeeId, Integer referenceId, Integer month) {
        SalaryPaidOrUnPaid salaryPaidOrUnPaid = salaryPaidOrUnPaidRepo.findByMonthAndSalaryPaidOrUnPaidAndReferenceId(month,employeeId,referenceId).orElseThrow(()->new RuntimeException("invalid month or ReferenceId"));
        System.out.println(salaryPaidOrUnPaid.getSalaryPaidOrUnPaid());
        salaryPaidOrUnPaid.setSalaryPaidOrUnPaid(true);
        salaryPaidOrUnPaidRepo.save(salaryPaidOrUnPaid);
        return "updated salary paid";
    }
    public Boolean gateSalaryPaidOrUnpaid(Integer employeeId, Integer referenceId, Integer month) {
        SalaryPaidOrUnPaid salaryPaidOrUnPaid = salaryPaidOrUnPaidRepo.findByMonthAndSalaryPaidOrUnPaidAndReferenceId(month,employeeId,referenceId).orElseThrow(()->new RuntimeException("invalid month or ReferenceId"));

        return salaryPaidOrUnPaid.getSalaryPaidOrUnPaid();
    }
}
