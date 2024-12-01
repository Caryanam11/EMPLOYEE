package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "EmployeeAttendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer referenceId;
    private Integer employeeId;
    @JsonIgnore
    private Integer month;
    private LocalDate date;


    //PRESENT
    //ANSENT
    //HALFDAY
    private String status;




}
