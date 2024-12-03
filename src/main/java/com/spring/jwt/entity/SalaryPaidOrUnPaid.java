package com.spring.jwt.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "SalaryPaidOrUnPaid")
@Data
public class SalaryPaidOrUnPaid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer month;
    private Boolean SalaryPaidOrUnPaid;

    private Integer referenceId;
    private Integer employeeId;

}
