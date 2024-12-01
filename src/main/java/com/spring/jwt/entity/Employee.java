package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_Id")
    @SequenceGenerator(name = "employee_Id", initialValue = 100000)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    private Integer referenceId;

    @Column(name = "SalaryPerMonth")
    private String Salary;

    @Column(name = "hire_date")
    private LocalDate hireDate;


}
