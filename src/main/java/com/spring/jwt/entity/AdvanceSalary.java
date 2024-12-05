package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AdvanceSalary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSalary {
    @Id
    private Integer advanceEmployeId;
    private Integer advanceAmount;
}
