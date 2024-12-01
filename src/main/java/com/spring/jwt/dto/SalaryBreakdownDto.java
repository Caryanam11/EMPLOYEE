package com.spring.jwt.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalaryBreakdownDto {


    private LocalDate date;


    //PRESENT
    //ANSENT
    //HALFDAY
    private String status;

}
