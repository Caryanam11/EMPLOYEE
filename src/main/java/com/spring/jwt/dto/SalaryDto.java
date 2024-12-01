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
    private List<SalaryBreakdownDto> salaryBreakdownDto;

    public SalaryDto(Integer employeeId, Integer halfDaySalary, Integer perDaySalary, Integer totalSalary, Integer totalSalaryCalculate, List<SalaryBreakdownDto> salaryBreakdownDto) {
        this.employeeId = employeeId;
        this.halfDaySalary = halfDaySalary;
        this.perDaySalary = perDaySalary;
        this.totalSalary = totalSalary;
        this.totalSalaryCalculate = totalSalaryCalculate;
        this.salaryBreakdownDto = salaryBreakdownDto;
    }
}
