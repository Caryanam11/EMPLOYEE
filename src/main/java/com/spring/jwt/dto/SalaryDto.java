package com.spring.jwt.dto;

import lombok.Data;

import java.util.List;

@Data
public class SalaryDto {

    private Integer employeeId;

    private Integer halfDaySalary;
    private Integer perDaySalary;
    private Integer totalSalary;
    private Integer totalSalaryCalculate;
    private Boolean SalaryPaidOrUnPaid;
    private List<SalaryBreakdownDto> salaryBreakdownDto;

    public SalaryDto(Integer employeeId, Integer halfDaySalary, Integer perDaySalary, Integer totalSalary, Integer totalSalaryCalculate, List<SalaryBreakdownDto> salaryBreakdownDto,
    Boolean salaryPaidOrUnPaid) {
        this.employeeId = employeeId;
        this.halfDaySalary = halfDaySalary;
        this.perDaySalary = perDaySalary;
        this.totalSalary = totalSalary;
        this.totalSalaryCalculate = totalSalaryCalculate;
        this.salaryBreakdownDto = salaryBreakdownDto;
        this.SalaryPaidOrUnPaid = salaryPaidOrUnPaid;

    }
}
