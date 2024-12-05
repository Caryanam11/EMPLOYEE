package com.spring.jwt.service;

import com.spring.jwt.dto.SalaryBreakdownDto;
import com.spring.jwt.dto.SalaryDto;
import com.spring.jwt.entity.AdvanceSalary;
import com.spring.jwt.entity.Employee;
import com.spring.jwt.entity.EmployeeAttendance;
import com.spring.jwt.entity.SalaryPaidOrUnPaid;
import com.spring.jwt.repository.AdvanceSalaryRepo;
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
    private AdvanceSalaryRepo advanceSalaryRepo;



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
        Optional<AdvanceSalary> advanceSalary = advanceSalaryRepo.findByEmployeeId(employeeId);
        Integer getFinalAmount = 0;



     if (advanceSalary.isPresent()){

          getFinalAmount =  advanceSalary.get().getAdvanceAmount();
        }
        return new SalaryDto(employeeId,halfDaySalary,perDaySalary,totalSalary,totalSalaryAsterBreakdown,salaryBreakdownDtos,salPaidOrNOt,getFinalAmount);

    }
    public Object advanceAVB(Integer employeeId) {
        return advanceSalaryRepo.findByEmployeeId(employeeId).orElseThrow(()->new RuntimeException("employee id not found by id"));
    }
    public String addAdvanceAVB(Integer employeeId,Integer amount) {
        Optional<AdvanceSalary> advanceSalary = advanceSalaryRepo.findByEmployeeId(employeeId);
        if (advanceSalary.isEmpty()){
            AdvanceSalary advanceSalary1 = new AdvanceSalary();
            advanceSalary1.setAdvanceAmount(amount);
            advanceSalary1.setAdvanceEmployeId(employeeId);
            advanceSalaryRepo.save(advanceSalary1);
        }else if (advanceSalary.isPresent()){
            Integer getFinalAmount = advanceSalary.get().getAdvanceAmount();


            advanceSalary.get().setAdvanceAmount(getFinalAmount+amount);
            advanceSalaryRepo.save(advanceSalary.get());

        }

        return "Advance added";

    }
    public Object deleteAdvanceAVB(Integer employeeId,Integer amount) {
        AdvanceSalary advanceSalary =  advanceSalaryRepo.findByEmployeeId(employeeId).orElseThrow(()->new RuntimeException("employee id not found by id"));

            Integer getFinalAmount = advanceSalary.getAdvanceAmount();

            if (advanceSalary.getAdvanceAmount() <= amount){
                getFinalAmount = amount - getFinalAmount;


                if (advanceSalary.getAdvanceAmount() < 0){
                    getFinalAmount = -(getFinalAmount);

                }

            }

            else if (advanceSalary.getAdvanceAmount() >= amount){
                getFinalAmount = getFinalAmount-amount ;


                if (advanceSalary.getAdvanceAmount() < 0){
                    getFinalAmount = -(getFinalAmount);

                }

            }

            advanceSalary.setAdvanceAmount(getFinalAmount);
            advanceSalaryRepo.save(advanceSalary);

        return "deleted salary paid";
    }
    public String updatedSalaryPaidStatus(Integer employeeId, Integer referenceId, Integer month) {
        SalaryPaidOrUnPaid salaryPaidOrUnPaid = salaryPaidOrUnPaidRepo.findByMonthAndSalaryPaidOrUnPaidAndReferenceId(month,employeeId,referenceId).orElseThrow(()->new RuntimeException("not found"));

        if (salaryPaidOrUnPaid.getSalaryPaidOrUnPaid()){
            salaryPaidOrUnPaid.setSalaryPaidOrUnPaid(false);
            salaryPaidOrUnPaidRepo.save(salaryPaidOrUnPaid);
        }

        return "updated salary paid";
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
